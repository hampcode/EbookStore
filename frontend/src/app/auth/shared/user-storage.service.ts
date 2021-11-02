import { Injectable } from '@angular/core';
import { SessionUser } from './session-user';

@Injectable({
  providedIn: 'root',
})
export class UserStorageService {
  private key: string = 'ng-auth';

  constructor() {}

  get access_token(): string {
    let user = localStorage.getItem(this.key);

    if (user) {
      let objUser = JSON.parse(user);
      return objUser.token;
    }

    return '';
  }

  get user(): SessionUser {
    let user = localStorage.getItem(this.key);

    if (user) {
      let objUser = JSON.parse(user);
      return objUser.user as SessionUser;
    }
    return new SessionUser();
  }

  get isAuthenticated(): boolean {
    return localStorage.getItem(this.key) !== null;
  }

  set(object: any): void {
    localStorage.setItem(this.key, JSON.stringify(object));
  }

  destroy(): void {
    localStorage.removeItem(this.key);
  }
}
