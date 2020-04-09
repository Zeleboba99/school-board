import {Component, OnInit} from '@angular/core';
import {CookieService} from 'angular2-cookie/core';
import {Router} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {User} from '../../models/user';
import {Role} from '../../models/role.enum';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public signInData = {username: '', password: ''};
  public serverError: any;
  constructor(private authService: AuthService, private router: Router, private cookieService: CookieService) { }

  private static chooseRole(roles): Role {
    let role = Role.STUDENT;
    if (roles.indexOf(Role.ADMIN) !== -1) {
      role = Role.ADMIN;
    } else if (roles.indexOf(Role.TEACHER) !== -1) {
      role = Role.TEACHER;
    }
    return role;
  }

  ngOnInit() {
    if (this.authService.userLoggedIn.getValue()) {
      this.router.navigate(['/news-board']);
    }
  }

  login() {
    // TODO validation and compare passwords
    this.authService.login(this.signInData)
      .subscribe(
        data => {
          if (data['token']) {
            this.authService.saveToken(data['token']);
            this.cookieService.put('username', data['username']);
            const user = new User(data['id'], data['username'], '', LoginComponent.chooseRole(data['roles']));
            this.cookieService.put('role', user.role);
            console.log(user.role);
            this.authService.userLoggedIn.next(user);
            this.router.navigate(['/news-board']);
          }},
        error => this.serverError = error.error.message
      );
  }
}
