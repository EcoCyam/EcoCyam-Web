import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../model/user";
import {Observable, of} from "rxjs";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseUrl: string;

  constructor(private http: HttpClient) {
    this.baseUrl = "http://localhost:8080/api/users";
  }

  login(user: User) {
    return this.http.post(`${this.baseUrl}/userExist`,user) as Observable<User>;
  }

  private handleError<T>(result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead
      return of(result as T);
    };
  }

  isLoggedIn() {
    // get the token from the localStorage as we have to work on this token.
    let token = localStorage.getItem('token');

    // check whether if token have something or it is null.
    return token;
  }

  logout(){
    localStorage.removeItem('token');
  }

  create(user: User) {
    return this.http.post(`${this.baseUrl}`,user) as Observable<User>;
  }
}
