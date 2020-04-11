import {Component, OnInit} from '@angular/core';
import {PostService} from '../../services/post.service';
import {Router} from '@angular/router';
import {Post} from '../../models/post';
import {AuthService} from '../../services/auth.service';
import {Role} from '../../models/role.enum';
import {NgxSpinnerService} from 'ngx-spinner';
import {PagePost} from '../../models/page-post';

@Component({
  selector: 'app-news-board',
  templateUrl: './news-board.component.html',
  styleUrls: ['./news-board.component.css']
})
export class NewsBoardComponent implements OnInit {

  public postPage: PagePost;
  selectedPage = 0;
  size = 5;
  page = 1;
  public posts: Post[] = [];
  public user;
  public role: Role;
  constructor(private postService: PostService,
              private router: Router,
              private authService: AuthService,
              private spinnerService: NgxSpinnerService
  ) { }

  ngOnInit() {
    this.spinnerService.show();
    // setTimeout(() => {
    //   this.spinnerService.hide();
    // }, 1000);
    this.user = this.authService.userLoggedIn.getValue();
    console.log(this.user.role);
    this.getPagePost(this.selectedPage, this.size);
    this.spinnerService.hide();
    // // mock
    // const mockPost = new Post(1, 'header', 'test of post la', 'author');
    // for (let i = 1; i <= 10; i++) {
    //   this.posts.push(mockPost);
    // }
  }

  getPagePost(page: number, size: number): void {
    this.postService.getAllPosts(page, size)
      // tslint:disable-next-line:no-shadowed-variable
      .subscribe(page => {
        this.postPage = page;
        this.posts = this.postPage.content;
      });
  }

  onPageSelect(page: number): void {
    console.log('selected page : ' + page);
    this.selectedPage = page;
    this.getPagePost(page, this.size);
  }
  onPostClick(post_id: number) {
    this.router.navigate(['post'], {queryParams: {post_id: post_id}});
  }

  onAddPostClick() {
    this.router.navigate(['edit-post']);
  }
}
