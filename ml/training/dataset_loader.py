# ml/training/dataset_loader.py
import pandas as pd
from db import get_connection


def load_full_dataset() -> pd.DataFrame:
    """
    Retorna DataFrame com:
    product_id | date | quantity
    """
    conn = get_connection()
    query = """
        SELECT
            product_id,
            sale_date AS date,
            quantity
        FROM sale
        ORDER BY sale_date
    """
    df = pd.read_sql(query, conn)
    conn.close()

    df["date"] = pd.to_datetime(df["date"])
    return df


DATA_PATH = "data/sales.csv"  # ajuste se necessário

def load_product_history(product_id: int) -> pd.DataFrame:
    df = pd.read_csv(DATA_PATH)

    df = df[df["product_id"] == product_id]

    return df
