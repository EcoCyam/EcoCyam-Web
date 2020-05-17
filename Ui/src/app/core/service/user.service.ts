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
    //this.baseUrl = "http://localhost:8080/api/users";
    this.baseUrl = "http://ecocyam-web.cfapps.io/api/users";
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
    localStorage.clear();
  }

  create(user: User) {
    return this.http.post(`${this.baseUrl}`,user) as Observable<User>;
  }

  update(user: User) {
    let currentUserId = Number(localStorage.getItem('token'));
    return this.http.put(`${this.baseUrl}/` + currentUserId,user) as Observable<User>;
  }

  getCurrentUser(){
    let currentUserId = Number(localStorage.getItem('token'));
    let url = this.baseUrl +'/'+ currentUserId;
    return this.http.get(url) as Observable<User>;
  }
}
