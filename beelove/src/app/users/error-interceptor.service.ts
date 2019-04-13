import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthenticationService } from './authentication.service';


@Injectable({
  providedIn: 'root'
})
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private authenticationService: AuthenticationService) {}

  /**
   * If 401 is returned from API user is automatically logged out
   */
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      return next.handle(request).pipe(catchError(err => {
        // TODO: change to check for status 401
          if (err.status === 402) {
              // auto logout if 401 response returned from api
              this.authenticationService.logout();
              location.reload(true);
          }

          const error = err.error.message || err.statusText;
          return throwError(error);
      }));
  }
}
