import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../api-service';

@Component({
  selector: 'app-scanner',
  standalone: false,
  templateUrl: './scanner.html',
  styleUrl: './scanner.css',
})
export class Scanner {


  token: string = '';
  scannedResult: string = '';

  constructor(
    private route: ActivatedRoute,
    private api: ApiService
  ) {}

  ngOnInit(): void {
    // ✅ Get token from email link
    this.route.queryParams.subscribe(params => {
      this.token = params['token'];
      console.log('Token:', this.token);
    });
  }

  // ✅ When QR is scanned
  handleQrCodeResult(result: string) {

    if (this.scannedResult) return; // prevent multiple scans

    this.scannedResult = result;
    console.log('Scanned:', result);

    const payload = {
      partcode: result,   // QR value
      token: this.token   // from URL
    };

    // ✅ Call backend
    this.api.scan(payload).subscribe({
      next: (res) => {
        console.log('✅ Scan success:', res);
        alert('Scan successful!');
      },
      error: (err) => {
        console.error('❌ Scan failed:', err);
        alert('Scan failed!');
      }
    });
  }


    //camera
    currentDevice: MediaDeviceInfo | undefined;
availableDevices: MediaDeviceInfo[] = [];

onCamerasFound(devices: MediaDeviceInfo[]) {
  this.availableDevices = devices;

  // Prefer back camera
  this.currentDevice = devices.find(device =>
    device.label.toLowerCase().includes('back')
  ) || devices[0];
}


}
