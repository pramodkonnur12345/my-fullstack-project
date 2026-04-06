import { Component } from '@angular/core';
import { ApiService } from '../api-service';

@Component({
  selector: 'app-upload-component',
  standalone: false,
  templateUrl: './upload-component.html',
  styleUrl: './upload-component.css',
})
export class UploadComponent {

  file!: File;

  constructor(private api: ApiService) {}

  onFileChange(event: any) {
    this.file = event.target.files[0];
  }

  upload() {
    this.api.uploadProducts(this.file).subscribe({
      next: () => alert('Uploaded'),
      error: err => alert(err.error)
    });
  }
}
