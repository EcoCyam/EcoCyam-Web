import { Component, OnInit, OnDestroy } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {User} from "../../core/model/user";
import {UserService} from "../../core/service/user.service";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit{
  loginForm: FormGroup;
  submitted = false;
  user: User;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService
  ){
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  ngOnInit() {
    if((this.userService.isLoggedIn() !== null) )
    {
      this.router.navigate(['/dashboard']);
    }else{
      this.router.navigate(['/login']);
    }
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    this.user = new User(this.loginForm.value);
    this.userService.login(this.user).pipe(first()).subscribe(data => {
      if(data){
        localStorage.setItem("token" , String(data.id));
        this.router.navigate(['/dashboard']);
      }else{
        alert("Merci de vérifier vos données");
      }
    });
  }
}
