import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SettingsService } from '../../core/services/settings';

@Component({
  selector: 'app-configuration',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './configurations.html',
  styleUrls: ['./configurations.css']
})
export class ConfigurationComponent {
  // 👇 Nome igualzinho ao que está no [(ngModel)] do HTML
  selectedDays: number;

  constructor(private settingsService: SettingsService) {
    // Busca o valor que já está salvo no "banco local" do navegador
    this.selectedDays = this.settingsService.getForecastDays();
  }

  // 👇 Nome igualzinho ao que está no (change) do HTML
  onDaysChange() {
    this.settingsService.setForecastDays(this.selectedDays);
  }
}
