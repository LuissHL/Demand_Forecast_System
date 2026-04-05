import { Component, ViewChild, ElementRef, ChangeDetectorRef } from '@angular/core'; // 👈 1. Importamos o ChangeDetectorRef
import { UploadService } from '../../core/services/upload';
import { CommonModule } from '@angular/common';
import { Chart } from 'chart.js/auto';
import { SettingsService } from '../../core/services/settings';
import { jsPDF } from 'jspdf';
import html2canvas from 'html2canvas';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.html',
  styleUrls: ['./upload.css'],
  standalone: true,
  imports: [CommonModule],
})
export class UploadComponent {
  @ViewChild('forecastChart') canvasRef!: ElementRef;

  fileName = '';
  preview: any[] = [];
  columns: string[] = [];
  statusMessage = '';
  chart: any;
  predictions: any[] = [];
  isProcessing = false;
  // 👈 2. Injetamos o cdr aqui no construtor
  constructor(
    private upload: UploadService,
    private cdr: ChangeDetectorRef,
    private settingsService: SettingsService
  ) {}

  async handleFile(file: File) {
    this.fileName = file.name;

    const text = await file.text();
    const rows = text.split('\n').map((r) => r.trim());

    // Pega o cabeçalho original do arquivo para sabermos ler os dados
    const header = rows[0].split(',').map(h => h.trim());

    // 👇 1. MUDANÇA: Forçamos a tabela a ter as 6 colunas oficiais do sistema!
    this.columns = ['date', 'quantity', 'price', 'is_promo', 'discount_pct', 'is_holiday'];

    this.preview = rows
      .slice(1)
      .filter((r) => r.length)
      .map((r) => {
        const values = r.split(',');
        const obj: any = {};

        // Lê os valores reais que vieram no CSV
        header.forEach((h, index) => {
          obj[h] = values[index];
        });

        // 👇 2. MUDANÇA: Injetamos os ZEROS para as colunas que o CSV não tem
        obj['is_promo'] = obj['is_promo'] !== undefined ? obj['is_promo'] : 0;
        obj['discount_pct'] = obj['discount_pct'] !== undefined ? obj['discount_pct'] : 0;
        obj['is_holiday'] = obj['is_holiday'] !== undefined ? obj['is_holiday'] : 0;

        return obj;
      });

    // Avisa o Angular que a tabela está pronta para aparecer!
    this.cdr.detectChanges();
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) this.handleFile(file);
  }

  onDrop(event: DragEvent) {
    event.preventDefault();
    const file = event.dataTransfer?.files[0];
    if (file) this.handleFile(file);
  }

  onDragOver(event: DragEvent) {
    event.preventDefault();
  }
  downloadTemplate() {
    // Criamos o cabeçalho oficial e uma linha de exemplo
    const csvData = "date,quantity,price,is_promo,discount_pct,is_holiday\n2024-02-15,50,22.50,1,15.0,0";

    // Transformamos esse texto num "Arquivo"
    const blob = new Blob([csvData], { type: 'text/csv' });
    const url = window.URL.createObjectURL(blob);

    // Criamos um link invisível, clicamos nele pelo código e depois apagamos
    const a = document.createElement('a');
    a.setAttribute('hidden', '');
    a.setAttribute('href', url);
    a.setAttribute('download', 'modelo_previsao_vendas.csv'); // Nome do arquivo que vai baixar
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
  }

  renderChart() {
    if (!this.canvasRef) return;
    const ctx = this.canvasRef.nativeElement.getContext('2d');

    const labels = this.predictions.map((p: any) => p.date);
    const data = this.predictions.map((p: any) => p.prediction);

    if (this.chart) this.chart.destroy();

    this.chart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: labels,
        datasets: [
          {
            label: 'Previsão de Vendas',
            data: data,
            borderColor: '#3b82f6',
            backgroundColor: 'rgba(59, 130, 246, 0.2)',
            fill: true,
            tension: 0.4,
          },
        ],
      },
      options: {
        scales: { y: { beginAtZero: true } },
      },
    });
  }

  // Certifique-se de que o SettingsService está no construtor lá em cima!
// constructor(private upload: UploadService, private cdr: ChangeDetectorRef, private settingsService: SettingsService) {}

processFile() {
  this.isProcessing = true;
  this.statusMessage = 'A inteligência artificial está a calcular...';

  // 1. Pega o número FRESQUINHO que você salvou na tela de Configurações
  const diasEscolhidos = this.settingsService.getForecastDays();

  // 2. Manda esse número na requisição (Tira o 6 daqui se ele estiver chumbado!)
  this.upload.sendPreviewToPython(this.preview, diasEscolhidos).subscribe({
    next: (response) => {
      this.predictions = response.predictions;
      this.renderChart();
      this.isProcessing = false;
      this.statusMessage = '';
      this.cdr.detectChanges();
    },
    error: () => {
      this.statusMessage = 'Erro na previsão.';
      this.isProcessing = false;
      this.cdr.detectChanges();
    }
  });
}

exportarParaPDF() {
    // 1. Encontra a div que marcamos com o ID
    const elemento = document.getElementById('relatorio-pdf');

    if (elemento) {
      // Pequeno truque para mostrar o cabeçalho no PDF e esconder depois
      const header = elemento.querySelector('.pdf-header') as HTMLElement;
      if (header) header.style.display = 'block';

      // 2. O html2canvas tira a "foto" da div
      html2canvas(elemento, { scale: 2 }).then((canvas) => {
        // Pega a imagem gerada
        const imageData = canvas.toDataURL('image/png');

        // 3. Configura a página do PDF (A4, formato retrato)
        const pdf = new jsPDF('p', 'mm', 'a4');

        // Medidas matemáticas para encaixar a imagem na folha A4
        const pdfWidth = pdf.internal.pageSize.getWidth();
        const pdfHeight = (canvas.height * pdfWidth) / canvas.width;

        // 4. Cola a foto no PDF e faz o download
        pdf.addImage(imageData, 'PNG', 0, 10, pdfWidth, pdfHeight);
        pdf.save('Relatorio_Previsao_Vendas.pdf');

        // Esconde o cabeçalho da tela novamente
        if (header) header.style.display = 'none';
      });
    }
  }
}
