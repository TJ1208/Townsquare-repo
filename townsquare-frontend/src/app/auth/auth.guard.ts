import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth/auth.service';
import { LoginService } from '../services/login/login.service';
import { UserService } from '../services/user/user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (this.authService.getToken() !== null) {
      // If using role-based authorization
      const role = route.data["roles"] as Array<string>;

      if(role) {
        // code to be implemented

        if (true) {
          return true;
        } else {
          this.router.navigate(['/forbidden'])
          return false;
        }
      }
    }

    this.router.navigate(['/login']);
    return false;
  }

}
