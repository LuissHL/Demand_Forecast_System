from pathlib import Path
import pandas as pd
import joblib

from training.make_features import make_features
from api.schema import MultiForecastRequest
from services.confidence import calculate_confidence

BASE_DIR = Path(__file__).resolve().parents[1]
MODEL_PATH = BASE_DIR / "models" / "global_model.pkl"

model = joblib.load(MODEL_PATH)


def predict_multiple_months(req: MultiForecastRequest):

    df = pd.DataFrame([item.model_dump() for item in req.history])
    df["date"] = pd.to_datetime(df["date"])

    predictions = []

    for step in range(req.months):

        confidence = calculate_confidence(df, step=step + 1)

        X = make_features(df)
        X_future = X.tail(1).copy()

        X_future["is_promo"] = req.is_promo
        X_future["discount_pct"] = req.discount_pct

        tree_preds = [
           tree.predict(X_future.values)[0]
           for tree in model.estimators_
        ]


        y_pred = sum(tree_preds) / len(tree_preds)
        y_min = min(tree_preds)
        y_max = max(tree_preds)

        next_date = df["date"].max() + pd.DateOffset(months=1)

        predictions.append({
            "date": next_date.strftime("%Y-%m-%d"),
            "prediction": round(float(y_pred), 2),
            "min": round(float(y_min), 2),
            "max": round(float(y_max), 2),
            "confidence": confidence
        })

        df = pd.concat([
            df,
            pd.DataFrame([{
                "date": next_date,
                "quantity": y_pred
            }])
        ], ignore_index=True)

    return {
     "predictions": predictions,
    
}

