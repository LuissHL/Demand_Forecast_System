import pandas as pd
import joblib
from sklearn.ensemble import RandomForestRegressor

from make_features import make_features

# ===============================
# 1. carregar dados históricos
# ===============================
df = pd.read_csv("data/sales.csv")

df["date"] = pd.to_datetime(df["date"])

# se não existir no csv, cria padrão
df["is_promo"] = df.get("is_promo", 0)
df["discount_pct"] = df.get("discount_pct", 0)
df["is_holiday"] = df.get("is_holiday", 0)

# ===============================
# 2. criar features
# ===============================
X = make_features(df)
y = df["quantity"]

# ===============================
# 3. treinar modelo
# ===============================
model = RandomForestRegressor(
    n_estimators=200,
    random_state=42
)

model.fit(X, y)

# ===============================
# 4. salvar modelo
# ===============================
joblib.dump(model, "models/global_model.pkl")

print("✅ Modelo treinado e salvo com sucesso!")
print("Features usadas:", model.feature_names_in_)
