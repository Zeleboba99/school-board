import { Component, OnInit } from '@angular/core';
import {PostService} from '../../services/post.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Post} from '../../models/post';
import {CommentService} from '../../services/comment.service';
import {Comment} from '../../models/comment';
import {AuthService} from '../../services/auth.service';
import {NgxSpinnerService} from 'ngx-spinner';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  public post: Post;
  public user;
  public deletedCommentId;
  public deletedPostId;
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
          this.commentService.getAllCommentsByPostId(post_id).subscribe(x => {
            this.post.comments = x;
            this.spinnerService.hide();
          });
        });
      }
    });
    // // mock
    // this.post = new Post(1, 'header', 'test of post la', 'author');
    // // mock
    // const mockComment = new Comment(1, 'it`s a comment', 'comment`s author', 1);
    // for (let i = 0; i <= 5; i++) {
    //   this.post.comments.push(mockComment);
    // }
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
    this.postService.deletePostById(this.deletedPostId).subscribe( res => this.router.navigate(['news-board']));
  }

  onAgreeDeleteComment() {
    this.commentService.deleteComment(this.post.post_id, this.deletedCommentId).subscribe(
      res =>  this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
        this.router.navigate(['post'], {queryParams: {post_id: this.post.post_id}}))
    );
  }
}
