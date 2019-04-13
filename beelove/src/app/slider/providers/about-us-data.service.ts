import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


const aboutUsContentLocation = 'api/about';
const aboutUsBannerLocation = 'api/banner';

@Injectable({
  providedIn: 'root'
})
export class AboutUsDataService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };
  constructor(private http: HttpClient) {
  }

  getAboutUsContent() {
    return this.http.get(aboutUsContentLocation, this.httpOptions);
  }

  getAboutUsBannerContent() {
    return this.http.get(aboutUsBannerLocation, this.httpOptions);
  }
}

