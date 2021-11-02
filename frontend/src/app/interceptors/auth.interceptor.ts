import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserStorageService } from '../auth/shared/user-storage.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private userStorageService: UserStorageService) {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    let token = this.userStorageService.access_token;

    if (token) {
      let authRequest = request.clone({
        headers: request.headers.set('Authorization', `Bearer ${token}`),
      });

      return next.handle(authRequest);
    }

    return next.handle(request);
  }
}
