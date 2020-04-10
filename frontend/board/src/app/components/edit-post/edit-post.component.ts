import { Component, OnInit } from '@angular/core';
import {PostService} from '../../services/post.service';
import {ActivatedRoute, Router} from '@angular/router';
import {CommentService} from '../../services/comment.service';
import {Post} from '../../models/post';
import {NgxSpinnerService} from 'ngx-spinner';

@Component({
  selector: 'app-edit-post',
  templateUrl: './edit-post.component.html',
  styleUrls: ['./edit-post.component.css']
})
export class EditPostComponent implements OnInit {

  public post: Post;
  constructor(private postService: PostService,
              private route: ActivatedRoute,
              private router: Router,
              private spinnerService: NgxSpinnerService
  ) { }

  ngOnInit() {
    this.post = new Post(0, '', '', '');
    this.route.queryParams.subscribe(params => {
      const post_id = params['post_id'];
      if (post_id) {
        this.spinnerService.show();
        setTimeout(() => {
          this.spinnerService.hide();
        }, 1000);
        this.postService.getPostById(post_id).subscribe(res => {
          this.post = res;
          this.spinnerService.hide();
        });
      }
    });
  }

  onPublishClick() {
    this.spinnerService.show();
    this.postService.createPost(this.post.header, this.post.text).subscribe(
      res => {
        this.router.navigate(['news-board']);
      }
    );
  }

  onEditClick() {
    this.spinnerService.show();
    this.postService.updatePost(this.post.post_id, this.post.header, this.post.text).subscribe(
      res => {
        this.router.navigate(['news-board']);
      }
    );
  }
}
