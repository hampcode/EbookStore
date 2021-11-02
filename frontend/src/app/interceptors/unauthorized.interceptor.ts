import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse,
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Router } from '@angular/router';
import { UserStorageService } from '../auth/shared/user-storage.service';
@Injectable()
export class UnauthorizedInterceptor implements HttpInterceptor {
  constructor(
    private userStorageService: UserStorageService,
    private router: Router
  ) {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401) {
          this.userStorageService.destroy();
          this.router.navigate(['/auth/login']);
        }

        return throwError(error);
      })
    );
  }
}
