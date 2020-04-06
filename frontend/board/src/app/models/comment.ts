export class Comment {
  public id: number;
  public text: string;
  public username: string;
  public post_id: number;

  constructor(id: number, text: string, username: string, post_id: number) {
    this.id = id;
    this.text = text;
    this.username = username;
    this.post_id = post_id;
  }
}
