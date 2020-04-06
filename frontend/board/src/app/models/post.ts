import {Comment} from './comment';

export class Post {
  public post_id: number;
  public header: string;
  public text: string;
  public username: string;
  public comments: Comment[] = [];


  constructor(post_id: number, header: string, text: string, username: string) {
    this.post_id = post_id;
    this.header = header;
    this.text = text;
    this.username = username;
  }
}
