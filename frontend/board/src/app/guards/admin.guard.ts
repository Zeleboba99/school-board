import {Inject, Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import { Observable } from 'rxjs/Observable';
import {AuthService} from '../services/auth.service';
import {Role} from '../models/role.enum';

@Injectable()
export class AdminGuard implements CanActivate {
  constructor(@Inject(AuthService) private authService: AuthService, private router: Router) {
  }
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.authService.userLoggedIn.getValue().role === Role.ADMIN) {
      return true;
    } else {
      this.router.navigate(['login']);
      return false;
    }
  }
}
