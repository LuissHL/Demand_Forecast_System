# para uso futuro

import numpy as np
import pandas as pd

def calculate_confidence(df: pd.DataFrame, step: int) -> str:
    history_len = len(df)

    mean = df["quantity"].mean()
    std = df["quantity"].std() or 0
    coef_var = std / mean if mean else 0

    score = 0

    # histórico
    if history_len >= 12:
        score += 2
    elif history_len >= 6:
        score += 1

    # estabilidade
    if coef_var < 0.2:
        score += 2
    elif coef_var < 0.4:
        score += 1

    # distância da previsão
    if step >= 5:
        score -= 2
    elif step >= 3:
        score -= 1

    if score >= 3:
        return "high"
    elif score >= 1:
        return "medium"
    return "low"
