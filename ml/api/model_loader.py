import joblib
from pathlib import Path

BASE_DIR = Path(__file__).resolve().parents[1]
MODEL_PATH = BASE_DIR / "models" / "global_model.pkl"

_model = None


def load_model():
    global _model

    if _model is None:
        _model = joblib.load(MODEL_PATH)

    return _model
