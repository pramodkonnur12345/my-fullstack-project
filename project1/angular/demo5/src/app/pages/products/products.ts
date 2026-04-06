import { Component } from '@angular/core';
import { Product } from '../../../product.model';
import { ApiService } from '../../api-service';

@Component({
  selector: 'app-products',
  standalone: false,
  templateUrl: './products.html',
  styleUrl: './products.css',
})
export class Products {


  products: Product[] = [];
  operator: any;

  constructor(private api: ApiService) {}

  ngOnInit() {
    this.operator = JSON.parse(localStorage.getItem('operator') || '{}');

    if (!this.operator?.type) {
      alert('No operator found. Please login again.');
      return;
    }

    this.loadProducts();
  }

  loadProducts() {
    this.api.getProductsByType(this.operator.type).subscribe({
      next: (res) => {this.products = res; console.log(res)},
      error: (err) => alert(err.error)
    });
  }


selectedQR: string | null = null;

openQR(qr: string) {
  this.selectedQR = qr;
}

closeQR() {
  this.selectedQR = null;
}




}
