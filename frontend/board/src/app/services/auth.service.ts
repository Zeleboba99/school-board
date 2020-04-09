import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {User} from '../models/user';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {CookieService} from 'angular2-cookie/core';
import {environment} from '../../environments/environment';

@Injectable()
export class AuthService {

  private base_url = '/api/auth';
  public userLoggedIn: BehaviorSubject<User> = new BehaviorSubject<User>(null);
  constructor(private http: HttpClient, private router: Router,
              private cookieService: CookieService) {
    if (this.cookieService.get('username')) {
          const username = this.cookieService.get('username');
          this.userLoggedIn.next(new User( 0, username, '', ''));
    }
  }

  login(signInData) {
    const username = signInData.username;
    const password = signInData.password;

    // TODO remove credentials into header
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': 'Basic'
      })
    };
    return this.http.post(this.base_url + '/signin', {username, password}, httpOptions);
  }

  saveToken(token: string) {
    // var expireDate = new Date().getTime() + (1000 * token.expires_in);
    this.cookieService.put(environment.ACCESS_TOKEN, token);
  }

  logout() {
    this.cookieService.remove(environment.ACCESS_TOKEN);
    this.cookieService.remove('role');
    this.cookieService.remove('username');
    this.userLoggedIn.next(null);
    this.router.navigate(['/login']);
  }

}
