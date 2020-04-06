import { Component, OnInit } from '@angular/core';
import {PostService} from '../../services/post.service';
import {ActivatedRoute, Router} from '@angular/router';
import {CommentService} from '../../services/comment.service';

@Component({
  selector: 'app-edit-comment',
  templateUrl: './edit-comment.component.html',
  styleUrls: ['./edit-comment.component.css']
})
export class EditCommentComponent implements OnInit {

  public post_id: number;
  public text = '';
  constructor(private postService: PostService,
              private router: Router,
              private route: ActivatedRoute,
              private commentService: CommentService) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.post_id = params['post_id'];
    });
  }

  onPublishClick() {
    this.commentService.createComment(this.post_id, this.text).subscribe(
      res => this.router.navigate(['post'], {queryParams: {post_id: this.post_id}})
    );
  }
}
