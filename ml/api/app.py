from fastapi import FastAPI
from typing import List
import pandas as pd
import joblib
from sklearn.ensemble import RandomForestRegressor

from training.make_features import make_features
from api.schema import MultiForecastRequest, SaleDTO
from fastapi.middleware.cors import CORSMiddleware

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # depois a gente restringe
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)
# =============================
# PREDICT (Atualizado com a Máquina do Tempo 🚀)
# =============================

@app.post("/predict/multi")
def predict_multi(request: MultiForecastRequest):
    print("Iniciando Auto-Treinamento e Previsão Recursiva!")

    # 1. Carrega o histórico que o usuário acabou de enviar pelo Angular
    df_history = pd.DataFrame([s.dict() for s in request.history])
    df_history["date"] = pd.to_datetime(df_history["date"])
    
    # Preenchimento de segurança para as colunas extras
    df_history["is_promo"] = df_history.get("is_promo", 0)
    df_history["discount_pct"] = df_history.get("discount_pct", 0.0)
    df_history["is_holiday"] = df_history.get("is_holiday", 0)

    # ==========================================
    # 🔥 O SEGREDO: AUTO-TREINAMENTO (ON-THE-FLY)
    # ==========================================
    # Extrai as features desse arquivo específico que o usuário mandou
    X_train = make_features(df_history)
    y_train = df_history["quantity"]

    # Cria uma IA novinha em folha e treina ELA com os dados exatos do cliente!
    model = RandomForestRegressor(n_estimators=100, random_state=42)
    model.fit(X_train, y_train)
    print("IA treinada com sucesso com os dados do arquivo!")
    # ==========================================

    last_price = df_history["price"].iloc[-1] if "price" in df_history.columns else 0.0
    future_steps = request.months 
    predictions = []

    # Copiamos o histórico para irmos "alimentando" ele a cada dia previsto
    current_df = df_history.copy()

    for i in range(future_steps):
        # Descobre a data de "amanhã"
        next_date = current_df["date"].max() + pd.Timedelta(days=1)

        # Cria uma linha falsa para esse novo dia
        next_row = pd.DataFrame({
            "date": [next_date],
            "quantity": [0.0], # 0.0 para evitar o FutureWarning do Pandas
            "price": [last_price],
            "is_promo": [0],
            "discount_pct": [0.0],
            "is_holiday": [0]
        })

        # Gruda esse novo dia no final do nosso histórico
        temp_df = pd.concat([current_df, next_row], ignore_index=True)

        # Roda o super make_features
        X_all = make_features(temp_df)
        
        # Pega SÓ a última linha (que é o dia que queremos prever agora)
        X_next_day = X_all.iloc[[-1]]

        # Previsão!
        pred_val = model.predict(X_next_day)[0]
        
        # Salva o valor para o Angular (Usando 'prediction' para o Java entender)
        predictions.append({
            "prediction": float(pred_val),
            "date": next_date.strftime('%d/%m')
        })

        # Atualiza a quantidade falsa pela previsão real
        temp_df.at[temp_df.index[-1], "quantity"] = pred_val
        
        # O histórico atualizado vira a base para o próximo passo
        current_df = temp_df

    return {
        "predictions": predictions
    }
@app.post("/train")

def train_model(sales: List[SaleDTO]):



    df = pd.DataFrame([s.dict() for s in sales])

    df["date"] = pd.to_datetime(df["date"])



    df["is_promo"] = df.get("is_promo", 0)

    df["discount_pct"] = df.get("discount_pct", 0)

    df["is_holiday"] = df.get("is_holiday", 0)



    X = make_features(df)

    y = df["quantity"]



    model = RandomForestRegressor(

        n_estimators=200,

        random_state=42

    )



    model.fit(X, y)

    joblib.dump(model, "models/global_model.pkl")



    return {"status": "trained", "records_used": len(df)}