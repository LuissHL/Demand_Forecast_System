import { Component, OnInit } from '@angular/core';
import { ForecastService } from '../../core/services/forecast';
import { ForecastItem } from '../../core/models/forecast-response.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-forecast',
  templateUrl: './forecast.html',
  styleUrls: ['./forecast.css'],
  standalone: true,
  imports: [CommonModule]
})
export class ForecastComponent implements OnInit {

  forecasts: ForecastItem[] = [];

  constructor(private forecastService: ForecastService) {}

  ngOnInit() {
    this.forecastService.getForecast().subscribe((res) => {
      this.forecasts = res.predictions;
    });
  }
}
