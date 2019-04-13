import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const blogContentLocation = 'api/posts';


@Injectable({
  providedIn: 'root'
})
export class BlogContentService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };
  constructor(private http: HttpClient) {
  }

  getBlogContent() {
    return this.http.get(blogContentLocation, this.httpOptions);
  }
}
