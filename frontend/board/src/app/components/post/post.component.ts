import {Component, OnInit} from '@angular/core';
import {PostService} from '../../services/post.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Post} from '../../models/post';
import {CommentService} from '../../services/comment.service';
import {AuthService} from '../../services/auth.service';
import {NgxSpinnerService} from 'ngx-spinner';
import {PageComment} from '../../models/page-comment';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  public commentPage: PageComment;
  selectedPage = 0;
  size = 5;
  page = 1;
  public post: Post;
  public user;
  public deletedCommentId;
  public deletedPostId;
  public serverError: any;
  constructor(private postService: PostService,
              private router: Router,
              private route: ActivatedRoute,
              private commentService: CommentService,
              private authService: AuthService,
              private spinnerService: NgxSpinnerService
  ) { }

  ngOnInit() {
    this.spinnerService.show();
    // setTimeout(() => {
    //   this.spinnerService.hide();
    // }, 1000);
    this.user = this.authService.userLoggedIn.getValue();
    this.route.queryParams.subscribe(params => {
      const post_id = params['post_id'];
      if (post_id !== null) {
        this.postService.getPostById(post_id).subscribe(res => {
          this.post = res;
          this.getPageComment(post_id, this.selectedPage, this.size);
          this.spinnerService.hide();
        },
          error => {
          this.spinnerService.hide();
          this.serverError = 'Новость не найдена';
          setTimeout(() => {
            this.router.navigate(['news-board']);
          }, 1000);
        });
      }
    });
  }

  getPageComment(post_id: number , page: number, size: number): void {
    this.commentService.getAllCommentsByPostId(post_id, page, size)
      // tslint:disable-next-line:no-shadowed-variable
      .subscribe(page => {
        this.commentPage = page;
        this.post.comments = this.commentPage.content;
      });
  }

  onPageSelect(page: number): void {
    console.log('selected page : ' + page);
    this.selectedPage = page;
    this.getPageComment(this.post.post_id, page, this.size);
  }

  onDeleteComment(comment_id: number) {
    this.deletedCommentId = comment_id;
  }

  onDeletePost(post_id: number) {
    this.deletedPostId = post_id;
  }

  onAddCommentClick() {
    this.router.navigate(['edit-comment'], {queryParams: {post_id: this.post.post_id}});
  }

  onEditPost(post_id: number) {
    this.router.navigate(['edit-post'], {queryParams: {post_id: post_id}});
  }

  onAgreeDeletePost() {
    this.postService.deletePostById(this.deletedPostId).subscribe( res => this.router.navigate(['news-board']),
      error => this.serverError = 'Ошибка при удалении новости');
  }

  onAgreeDeleteComment() {
    this.commentService.deleteComment(this.post.post_id, this.deletedCommentId).subscribe(
      res =>  this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
        this.router.navigate(['post'], {queryParams: {post_id: this.post.post_id}})),
      error => {this.serverError = 'Ошибка при удалении комментария';
      console.log(this.serverError); }
    );
  }
}
