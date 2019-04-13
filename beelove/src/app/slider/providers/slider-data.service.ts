import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const URL = '/api/slider';

@Injectable({
  providedIn: 'root'
})
export class SliderDataService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };
  constructor(private http: HttpClient) {
  }

  getData() {
    return this.http.get(URL, this.httpOptions);
  }
}
