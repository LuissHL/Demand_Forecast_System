import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  // Se você tiver uma rota de upload no Python depois, pode mudar aqui também
  private apiUrl = 'http://localhost:8080/api/upload';

  constructor(private http: HttpClient) {}

  // ... (uploadFile continua igual)

  sendPreviewToPython(rows: any[], months: number): Observable<any> {
    return this.http.post('http://localhost:8080/api/forecast', {
      product_id: 1,
      months: months,
      history: rows.map(r => ({
        date: r.date,
        quantity: Number(r.quantity),
        price: Number(r.price),
        // 👇 Adicionamos valores padrão (0) para o Java não reclamar de dados nulos
        is_promo: r.is_promo ? Number(r.is_promo) : 0,
        discount_pct: r.discount_pct ? Number(r.discount_pct) : 0.0,
        is_holiday: r.is_holiday ? Number(r.is_holiday) : 0
      }))
    });
  }
}
