import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from './models/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { NotifierService } from 'angular-notifier';

const loginLocation = 'auth/oauth/token';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;
  httpOptions = {
    headers: new HttpHeaders({
      'Authorization': 'Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0'
    })
  };
  constructor(
    private httpClient: HttpClient,
    private notifierService: NotifierService) {
    this.currentUserSubject = new BehaviorSubject<User>(
      JSON.parse(localStorage.getItem('currentUser'))
    );
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }
  login(username: String, password: String) {
    return this.httpClient
      .post<any>(`${loginLocation}?grant_type=password&username=${username}&password=${password}`,
      null, this.httpOptions)
      .pipe(
        map(user => {
          if (user && user.access_token) {
            localStorage.setItem('access_token', JSON.stringify({
              user: username,
              access_token: user.access_token
            }));
            this.currentUserSubject.next(user);
          } else {
          }
        })
      );
  }


  logout() {
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
}
