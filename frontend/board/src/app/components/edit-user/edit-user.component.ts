import { Component, OnInit } from '@angular/core';
import {PostService} from '../../services/post.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AdminService} from '../../services/admin.service';
import {User} from '../../models/user';
import {Role} from '../../models/role.enum';
import {NgxSpinnerService} from 'ngx-spinner';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  public user: User;
  public role = 'student';
  constructor(private adminService: AdminService,
              private route: ActivatedRoute,
              private router: Router,
              private spinnerService: NgxSpinnerService
  ) { }

  ngOnInit() {
    this.user = new User(0, '', '', Role.STUDENT);
    let user_id = 0;
    this.route.queryParams.subscribe(params => {
      user_id = params['user_id'];
      if (user_id !== 0) {
        // this.spinnerService.show();
        // setTimeout(() => {
        //   this.spinnerService.hide();
        // }, 1000);
        this.adminService.getUserById(user_id).subscribe(res => {
          this.user = res;
          this.user.password = '';
          this.spinnerService.hide();
        });
      }
    });
  }


  onAddUserClick() {
    if (this.role === 'student') {
      this.user.role = Role.STUDENT;
    } else if (this.role === 'teacher') {
      this.user.role = Role.TEACHER;
    }
    this.adminService.createNewUser(this.user.username, [this.user.role], this.user.password).subscribe(
      res => this.router.navigate(['/admin'])
    );
  }

  onEditUserClick() {
    this.adminService.updateUser(this.user.user_id, this.user.username, [this.user.role], this.user.password).subscribe(
      res => this.router.navigate(['/admin'])
    );
  }
  randomPass (length, addUpper, addSymbols, addNums) {
    const lower = "abcdefghijklmnopqrstuvwxyz";
    const upper = addUpper ? lower.toUpperCase() : "";
    const nums = addNums ? "0123456789" : "";
    const symbols = addSymbols ? "!#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~" : "";

    const all = lower + upper + nums + symbols;
    while (true) {
      let pass = "";
      for (let i = 0; i < length; i++) {
        pass += all[ Math.random() * all.length | 0];
      }

      // criteria:
      if (!/[a-z]/.test(pass)) continue; // lowercase is a must
      if (addUpper && !/[A-Z]/.test(pass)) continue; // check uppercase
      if (addSymbols && !/\W/.test(pass)) continue; // check symbols
      if (addNums && !/\d/.test(pass)) continue; // check nums

      this.user.password = pass;
      return pass; // all good
    }
  }
}
