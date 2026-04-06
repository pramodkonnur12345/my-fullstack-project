import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../product.model';

@Injectable({
  providedIn: 'root',
})
export class ApiService {


  // baseUrl = 'https://localhost:8080/employee2';
  baseUrl = 'https://subalgebraic-diffident-tarsha.ngrok-free.dev/employee2';
  // baseUrl = 'http://10.55.81.39:8080/employee2'; -- same wifi
  // baseUrl = '/employee2';  //proxy.config.json
  // to run in cmd --> ng serve --host 0.0.0.0 --proxy-config proxy.conf.json

  constructor(private http: HttpClient) {}

  // LOGIN
  login(opname: string) {
    return this.http.post(`${this.baseUrl}/operators/login`, { opname });
  }

  // UPLOAD PRODUCTS
  uploadProducts(file: File) {
    const formData = new FormData();
    formData.append('file', file);

    return this.http.post(`${this.baseUrl}/products/upload`, formData);
  }

  // GET PRODUCTS
  getProducts(type: string) {
    return this.http.get(`${this.baseUrl}/products/${type}`);
  }

  getProductsByType(type: string) {
  return this.http.get<Product[]>(`${this.baseUrl}/products/${type}`);
}

  // SCAN
  scan(data: any) {
    return this.http.post(`${this.baseUrl}/scan`, data);
  }
}
