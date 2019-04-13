import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const checkoutPath = 'api/checkout';
@Injectable({
  providedIn: 'root'
})
export class CheckoutService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };
  // TODO: check if user logged in & prefill username info
  // save basket in localstorage
  // save address and users in a separate database.
  constructor(private http: HttpClient) {}

  placeOrder(order) {
   return  this.http.post(checkoutPath, order,  this.httpOptions);
  }
}
