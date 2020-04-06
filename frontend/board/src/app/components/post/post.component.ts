import { Component, OnInit } from '@angular/core';
import {PostService} from '../../services/post.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Post} from '../../models/post';
import {CommentService} from '../../services/comment.service';
import {Comment} from '../../models/comment';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  public post: Post;
  constructor(private postService: PostService,
              private router: Router,
              private route: ActivatedRoute,
              private commentService: CommentService) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      const post_id = params['post_id'];
      if (post_id) {
        this.postService.getPostById(post_id).subscribe(res => {
          this.post = res;
          this.commentService.getAllCommentsByPostId(post_id).subscribe(x => this.post.comments = x);
        });
      }
    });
    // mock
    this.post = new Post(1, 'header', 'test of post la', 'author');
    // mock
    const mockComment = new Comment(1, 'it`s a comment', 'comment`s author', 1);
    for (let i = 0; i <= 5; i++) {
      this.post.comments.push(mockComment);
    }
  }

  onDeleteComment(comment_id: number) {
    this.commentService.deleteComment(this.post.post_id, comment_id).subscribe(
      res =>  this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
        this.router.navigate(['post'], {queryParams: {post_id: this.post.post_id}}))
    );
  }

  onDeletePost(post_id: number) {
    this.postService.deletePostById(post_id).subscribe( res => this.router.navigate(['news-board']));
  }

  onAddCommentClick() {
    this.router.navigate(['edit-comment'], {queryParams: {post_id: this.post.post_id}});
  }

  onEditPost(post_id: number) {
    this.router.navigate(['edit-post'], {queryParams: {post_id: post_id}});
  }
}
