import { Component, OnInit } from '@angular/core';
import {PostService} from '../../services/post.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AdminService} from '../../services/admin.service';
import {User} from '../../models/user';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  public user: User;
  constructor(private adminService: AdminService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.user = new User(0, '', '', '');
    this.route.queryParams.subscribe(params => {
      const user_id = params['user_id'];
      if (user_id) {
        this.adminService.getUserById(user_id).subscribe(res => {
          this.user = res;
        });
      }
    });
  }


  onAddUserClick() {

  }

  onEditUserClick() {

  }
}
