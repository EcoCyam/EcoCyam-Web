import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {User} from "../../core/model/user";
import {UserService} from "../../core/service/user.service";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  registerForm: FormGroup;
  submitted = false;
  user: User;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService
  ){
    this.registerForm = this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }
    this.user = new User(this.registerForm.value);
    this.userService.create(this.user).pipe(first()).subscribe(data => {
      localStorage.setItem("token" , data.username);
      localStorage.setItem("email" , data.email);
      localStorage.setItem("password" , data.password);
      this.router.navigate(['/dashboard']);
    });
  }
}
