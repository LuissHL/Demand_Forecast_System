# db.py
import psycopg2

def get_connection():
    return psycopg2.connect(
        host="localhost",
        port=5432,
        user="postgres",
        password="forever123",
        database="demand_forecast"
    )


def save_model_to_db(product_id: int, model_bytes: bytes):
    conn = get_connection()
    cur = conn.cursor()

    cur.execute("""
        INSERT INTO product_models (product_id, model_data, trained_at, version)
        VALUES (%s, %s, NOW(), 1)
        ON CONFLICT (product_id)
        DO UPDATE SET
            model_data = EXCLUDED.model_data,
            trained_at = NOW(),
            version = product_models.version + 1
    """, (product_id, model_bytes))

    conn.commit()
    cur.close()
    conn.close()


def load_model_from_db(product_id: int):
    conn = get_connection()
    cur = conn.cursor()

    cur.execute("""
        SELECT model_data
        FROM product_models
        WHERE product_id = %s
    """, (product_id,))

    row = cur.fetchone()

    cur.close()
    conn.close()

    return row[0] if row else None

def get_all_products():
    """
    Retorna lista de product_id existentes
    """
    conn = get_connection()
    cur = conn.cursor()

    cur.execute("SELECT id FROM product")
    rows = cur.fetchall()

    cur.close()
    conn.close()

    return [r[0] for r in rows]


def get_sales_history_for_product(product_id: int):
    """
    Retorna histórico no formato:
    [{"date": "YYYY-MM-DD", "quantity": int}]
    """
    conn = get_connection()
    cur = conn.cursor()

    cur.execute("""
        SELECT sale_date, quantity
        FROM sale
        WHERE product_id = %s
        ORDER BY sale_date
    """, (product_id,))

    rows = cur.fetchall()

    cur.close()
    conn.close()

    return [
        {"date": r[0].strftime("%Y-%m-%d"), "quantity": r[1]}
        for r in rows
    ]


def load_model_info(product_id: int):
    conn = get_connection()
    cur = conn.cursor()

    cur.execute("""
        SELECT version, trained_at
        FROM product_models
        WHERE product_id = %s
    """, (product_id,))

    row = cur.fetchone()

    cur.close()
    conn.close()

    return {"version": row[0], "trained_at": row[1]} if row else None
