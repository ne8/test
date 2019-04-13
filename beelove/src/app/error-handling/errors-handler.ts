import { ErrorHandler, Injectable } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ErrorsHandler implements ErrorHandler {
  handleError(error: any): void {
    console.log('it happens');
    console.log(error);
    if (error instanceof HttpErrorResponse) {
      console.log('htttp');
    }
  }
}
