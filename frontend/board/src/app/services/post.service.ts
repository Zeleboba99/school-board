import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Post} from '../models/post';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class PostService {

  private base_url = '/api/posts';
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  constructor(private http: HttpClient) { }

  getAllPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(this.base_url);
  }

  getPostById(post_id: number): Observable<Post> {
    return this.http.get<Post>(this.base_url + '/' + post_id);
  }

  createPost(header: string, text: string) {
    return this.http.post(this.base_url, {header, text}, this.httpOptions);
  }

  deletePostById(post_id: number) {
    return this.http.delete(this.base_url + '/' + post_id);
  }
}
