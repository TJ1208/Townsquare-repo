import { Injectable } from '@angular/core';
import { User } from 'src/app/models/User';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  // If using role-based authentication
  public setRoles(roles: []) {
    localStorage.setItem("roles", JSON.stringify(roles));
  }
  
  public getRoles(): [] {
    let storage: any = localStorage.getItem("roles");
    return JSON.parse(storage);
  }

  // Setting and retrieving current jwt token
  public setToken(jwtToken: string): void {
    localStorage.setItem("jwtToken", jwtToken);
  }

  public getToken(): string {
    let jwtToken: any = localStorage.getItem("jwtToken");
    return jwtToken;
  }

  // Used for logging out current user
  public clear(): void {
    localStorage.clear();
  }

  public isLoggedIn(): boolean {
    return this.getToken() != null;
  }
}
