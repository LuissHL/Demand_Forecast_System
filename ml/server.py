# server.py
from fastapi import FastAPI, HTTPException, Query
from pydantic import BaseModel
from typing import List

import pandas as pd

from api.model_loader import load_model
from training.make_features import make_features
from training.dataset_loader import load_product_history

app = FastAPI(title="Demand Forecast ML API")

model = load_model()

# =======================
# Schemas
# =======================

class ForecastItem(BaseModel):
    month: str
    prediction: float
    min: float
    max: float

class ForecastResponse(BaseModel):
    product_id: int
    predictions: List[ForecastItem]

class MultiForecastRequest(BaseModel):
    product_id: int
    start_month: str   # YYYY-MM
    months_ahead: int = Query(6, ge=1, le=24)


# =======================
# Endpoints
# =======================

@app.post("/forecast", response_model=ForecastResponse)
def forecast_endpoint(req: MultiForecastRequest):
    try:
        df = load_product_history(req.product_id)

        if df.empty:
            raise HTTPException(status_code=404, detail="No history for product")

        df["date"] = pd.to_datetime(df["date"])

        current_date = pd.to_datetime(req.start_month + "-01")
        MAE = 0.75  # futuramente carregar do treino

        predictions = []

        for _ in range(req.months_ahead):
            df_future = pd.concat(
    [
        df,
        pd.DataFrame([{
            "product_id": req.product_id,
            "date": current_date,
            "quantity": None,
            "is_promo": int(req.is_promo),
            "discount_pct": req.discount_pct
        }])
    ],
    ignore_index=True
)

            X = make_features(df_future)
            X_future = X.tail(1)

            pred = float(model.predict(X_future)[0])

            predictions.append({
                "month": current_date.strftime("%Y-%m"),
                "prediction": round(pred, 2),
                "min": round(max(0, pred - MAE), 2),
                "max": round(pred + MAE, 2),
            })

            # feedback loop
            df = pd.concat(
                [
                    df,
                    pd.DataFrame([{
                        "product_id": req.product_id,
                        "date": current_date,
                        "quantity": pred
                    }])
                ],
                ignore_index=True
            )

            current_date += pd.DateOffset(months=1)

        return {
            "product_id": req.product_id,
            "predictions": predictions
        }

    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
