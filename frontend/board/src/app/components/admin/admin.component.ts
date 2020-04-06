import { Component, OnInit } from '@angular/core';
import {User} from '../../models/user';
import {ActivatedRoute, Router} from '@angular/router';
import {AdminService} from '../../services/admin.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  public users: User[] = [];
  public deleted_id = 0;
  constructor(private adminService: AdminService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.adminService.getAllUsers().subscribe(
      res => this.users = res
    );
    // mock
    const user = new User(1, 'username', 'password', 'student');
    for (let i = 1; i <= 10; i++) {
      this.users.push(user);
    }
  }

  onDeleteUser() {
    this.adminService.deleteUser(this.deleted_id).subscribe(
      res =>  this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
        this.router.navigate(['admin']))
    );
  }

  onNewUserClick() {
    this.router.navigate(['edit-user']);
  }

  onEditUser(user_id: number) {
    this.router.navigate(['edit-user'], {queryParams: {user_id: user_id}});
  }

  setDeleted(user_id: number) {
    this.deleted_id = user_id;
  }
}
