import {Component, OnInit} from '@angular/core';
import {User} from '../../models/user';
import {ActivatedRoute, Router} from '@angular/router';
import {AdminService} from '../../services/admin.service';
import {Role} from '../../models/role.enum';
import {NgxSpinnerService} from 'ngx-spinner';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  public users: User[] = [];
  public deleted_id = 0;
  public serverError = '';
  constructor(private adminService: AdminService,
              private router: Router,
              private route: ActivatedRoute,
              private spinnerService: NgxSpinnerService
  ) { }

  ngOnInit() {
    this.spinnerService.show();
    // setTimeout(() => {
    //   this.spinnerService.hide();
    // }, 1000);
    this.adminService.getAllUsers().subscribe(
      res => {
        this.users = res.filter(x => (x.role !== Role.ADMIN ));
        this.spinnerService.hide();
      }
    );
  }

  onDeleteUser() {
    this.adminService.deleteUser(this.deleted_id).subscribe(
      res =>  this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
        this.router.navigate(['admin'])),
      error => this.serverError = 'Не удалось удалить пользователя'
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
