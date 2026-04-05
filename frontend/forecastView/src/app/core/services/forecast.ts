import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ForecastResponse } from '../models/forecast-response.model';

@Injectable({
  providedIn: 'root'
})
export class ForecastService {

  private url = 'http://localhost:8000/forecast/multi';

  constructor(private http: HttpClient) {}

  getForecast(): Observable<ForecastResponse> {
    return this.http.get<ForecastResponse>(this.url);
  }
}
