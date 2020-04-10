import {Role} from './role.enum';

export class User {
  public user_id: number;
  public username: string;
  public password: string;
  public role: Role;

  constructor(id: number, username: string, password: string, role: Role) {
    this.user_id = id;
    this.username = username;
    this.password = password;
    this.role = role;
  }
}
