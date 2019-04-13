import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const productsLocation = 'api/products';


@Injectable({
  providedIn: 'root'
})
export class ProductsDataService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };
  constructor(private http: HttpClient) {
  }

  getProucts() {
    return this.http.get(productsLocation, this.httpOptions);
  }}
