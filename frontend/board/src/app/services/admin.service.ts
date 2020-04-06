import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {User} from '../models/user';

@Injectable()
export class AdminService {

  private base_url = '/api/admin/users';
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.base_url);
  }

  createNewUser(username: string, roles: string[], password: string) {
    return this.http.post(this.base_url, {username, roles, password}, this.httpOptions);
  }

  updateUser(user_id: number, username: string, roles: string[], password: string) {
    return this.http.put(this.base_url + '/' + user_id, {username, roles, password}, this.httpOptions);
  }

  deleteUser(user_id: number) {
    return this.http.delete(this.base_url + '/' + user_id);
  }

  getUserById(user_id: number): Observable<User> {
    // TODO add endpoint to backend
    return this.http.get<User>(this.base_url + '/' + user_id);
  }
}
