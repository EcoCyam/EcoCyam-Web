export class User {

  id: number;
  email: string;
  password: string;
  username: string;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }

}
