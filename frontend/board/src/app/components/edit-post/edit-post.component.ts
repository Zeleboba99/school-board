import { Component, OnInit } from '@angular/core';
import {PostService} from '../../services/post.service';
import {ActivatedRoute, Router} from '@angular/router';
import {CommentService} from '../../services/comment.service';
import {Post} from '../../models/post';

@Component({
  selector: 'app-edit-post',
  templateUrl: './edit-post.component.html',
  styleUrls: ['./edit-post.component.css']
})
export class EditPostComponent implements OnInit {

  public post: Post;
  constructor(private postService: PostService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      const post_id = params['post_id'];
      if (post_id) {
        this.postService.getPostById(post_id).subscribe(res => {
          this.post = res;
        });
      }
    });
  }

  onPublishClick() {
    this.postService.createPost(this.post.header, this.post.text).subscribe(
      res => this.router.navigate(['news-board'])
    );
  }

  onEditClick() {
    // TODO add endpoint to update
  }
}
