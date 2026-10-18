import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly storageKey = 'employee-hub-auth';

  login(username: string, password: string): boolean {
    const isValid = username === 'admin' && password === 'admin';

    if (isValid) {
      localStorage.setItem(this.storageKey, 'true');
    }

    return isValid;
  }

  logout(): void {
    localStorage.removeItem(this.storageKey);
  }

  isAuthenticated(): boolean {
    return localStorage.getItem(this.storageKey) === 'true';
  }
}
