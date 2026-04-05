export interface ForecastItem {
  date: string;
  prediction: number;
  min: number;
  max: number;
  confidence: number;
}

export interface ForecastResponse {
  productId: number;
  predictions: ForecastItem[];
}
