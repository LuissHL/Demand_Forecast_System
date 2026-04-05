import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  // Se tiver uma rota de upload no Python depois, pode mudar aqui também. Por enquanto, só tenho a rota de forecast mesmo.
  private apiUrl = 'http://localhost:8080/api/upload';

  constructor(private http: HttpClient) {}


  sendPreviewToPython(rows: any[], months: number): Observable<any> {
    return this.http.post('http://localhost:8080/api/forecast', {
      product_id: 1,
      months: months,
      history: rows.map(r => ({
        date: r.date,
        quantity: Number(r.quantity),
        price: Number(r.price),
        // Adicionei valores padrão (0) para o Java não reclamar de dados nulos  . tipo um fallback caso o CSV não tenha essas colunas, o sistema ainda funciona normalmente
        is_promo: r.is_promo ? Number(r.is_promo) : 0,
        discount_pct: r.discount_pct ? Number(r.discount_pct) : 0.0,
        is_holiday: r.is_holiday ? Number(r.is_holiday) : 0
      }))
    });
  }
}
