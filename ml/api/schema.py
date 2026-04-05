from pydantic import BaseModel
from typing import Optional, List

class SaleDTO(BaseModel):
    date: str
    quantity: int
    price: Optional[float] = 0
    is_promo: Optional[int] = 0
    discount_pct: Optional[float] = 0
    is_holiday: Optional[int] = 0


class MultiForecastRequest(BaseModel):
    product_id: Optional[int] = None       
    history: List[SaleDTO]
    months: int
    is_promo: int = 0                      
    discount_pct: float = 0
