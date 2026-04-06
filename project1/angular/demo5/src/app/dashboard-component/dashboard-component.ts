import { Component } from '@angular/core';
import { ApiService } from '../api-service';
import { BarcodeFormat } from '@zxing/library';


@Component({
  selector: 'app-dashboard-component',
  standalone: false,
  templateUrl: './dashboard-component.html',
  styleUrl: './dashboard-component.css',
})
export class DashboardComponent {

  products: any[] = [];
  operator: any;
  scanInput = '';

   // 👇 Enable both QR + Barcode
  allowedFormats = [
    BarcodeFormat.QR_CODE,
    BarcodeFormat.CODE_128,
    BarcodeFormat.EAN_13,
    BarcodeFormat.EAN_8
  ];

  constructor(private api: ApiService) {}

  ngOnInit() {
    this.operator = JSON.parse(localStorage.getItem('operator') || '{}');
    this.loadProducts();
  }

   // ✅ Camera scan
  onScanSuccess(result: string) {

    console.log('Scanned:', result);
    this.processScan(result);
  }

  // ✅ Manual fallback
  scanManual() {
    if (!this.scanInput) return;
    this.processScan(this.scanInput);
  }

  // 🔥 Common scan handler
  processScan(partcode: string) {

    const payload = {
      partcode: partcode,
      operatorId: this.operator.id
    };

     this.api.scan(payload).subscribe({
      next: () => {
        this.scanInput = '';
        this.loadProducts();
        this.playBeep(); // optional
      },
      error: err => alert(err.error)
    });
  }



  loadProducts() {
    this.api.getProducts(this.operator.type).subscribe((res: any) => {
      this.products = res;
      this.sortProducts();
    });
  }


  // 🔊 Optional beep sound
  playBeep() {
    const audio = new Audio('assets/beep.mp3');
    audio.play();
  }

  scan() {
    const payload = {
      partcode: this.scanInput,
      operatorId: this.operator.id
    };

    this.api.scan(payload).subscribe({
      next: () => {
        this.scanInput = '';
        this.loadProducts(); // refresh
      },
      error: err => alert(err.error)
    });
  }

  sortProducts() {
    this.products.sort((a, b) => {
      if (!a.scanned && b.scanned) return -1;
      if (a.scanned && !b.scanned) return 1;
      return 0;
    });
  }
}
