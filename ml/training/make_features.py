import pandas as pd

def make_features(df: pd.DataFrame) -> pd.DataFrame:
    df_features = df.sort_values("date").copy()

    # 1. Sazonalidade (O calendário)
    df_features["month"] = df_features["date"].dt.month
    df_features["year"] = df_features["date"].dt.year
    df_features["day_of_week"] = df_features["date"].dt.dayofweek
    df_features["is_weekend"] = df_features["day_of_week"].apply(lambda x: 1 if x >= 5 else 0)
    df_features["trend"] = range(len(df_features))

    # 2. Inércia do Passado (Os seus Lags Profissionais!)
    df_features["lag_1"] = df_features["quantity"].shift(1)
    df_features["lag_2"] = df_features["quantity"].shift(2)
    df_features["lag_3"] = df_features["quantity"].shift(3)

    # 3. Variáveis de Negócio
    df_features["is_promo"] = df_features.get("is_promo", 0)
    df_features["discount_pct"] = df_features.get("discount_pct", 0)
    df_features["is_holiday"] = df_features.get("is_holiday", 0)

    # Limpeza
    df_features = df_features.drop(columns=["date"])
    if "quantity" in df_features.columns:
        df_features = df_features.drop(columns=["quantity"])

    df_features = df_features.fillna(0).infer_objects(copy=False)
    
    return df_features