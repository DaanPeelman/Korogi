import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationService } from "../../services/authentication/authentication-service";

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {

  constructor(
      private authenticationService: AuthenticationService
  ) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (this.authenticationService.isLoggedIn()) {
      request = request.clone({
        setHeaders: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Authorization': 'Bearer ' + this.authenticationService.getToken()
        }
      });
    }

    return next.handle(request);
  }
}
