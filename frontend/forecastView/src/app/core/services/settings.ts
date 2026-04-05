import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SettingsService {
  // Padrão: 45 dias
  private forecastDays: number = 45;

  constructor() {
    // Quando o serviço inicia, tenta buscar se o usuário já tinha salvo algo antes
    const savedDays = localStorage.getItem('forecastDays');
    if (savedDays) {
      this.forecastDays = parseInt(savedDays, 10);
    }
  }

  getForecastDays(): number {
    return this.forecastDays;
  }

  setForecastDays(days: number): void {
    this.forecastDays = days;
    localStorage.setItem('forecastDays', days.toString()); // Salva no navegador!
  }
}
