export class User {
  public id: number;
  public username: string;
  public password: string;
  public role: string;

  constructor(id: number, username: string, password: string, role: string) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = role;
  }
}
