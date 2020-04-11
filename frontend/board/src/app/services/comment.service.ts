import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Comment} from '../models/comment';
import {PageComment} from '../models/page-comment';

@Injectable()
export class CommentService {

  private base_url = '/api';
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  constructor(private http: HttpClient) { }

  getAllCommentsByPostId(post_id: number, page: number, size: number): Observable<PageComment> {
    return this.http.get<PageComment>(this.base_url + '/posts/' + post_id + '/comments/?page=' + page + '&size=' + size);
  }

  createComment(post_id: number, text: string) {
    return this.http.post(this.base_url + '/posts/' + post_id + '/comments', {text}, this.httpOptions);
  }

  deleteComment(post_id: number, comment_id: number) {
    return this.http.delete(this.base_url + '/posts/' + post_id + '/comments/' + comment_id);
  }
}
