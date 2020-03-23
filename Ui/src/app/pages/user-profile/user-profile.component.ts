import { Component, OnInit } from '@angular/core';
import {UserService} from "../../core/service/user.service";
import {debounceTime, first} from "rxjs/operators";
import {User} from "../../core/model/user";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subject} from "rxjs";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  user: User;
  editForm: FormGroup;
  private _success = new Subject<string>();

  staticAlertClosed = false;
  successMessage = '';

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder,
  ) {
  }

  ngOnInit() {
    setTimeout(() => this.staticAlertClosed = true, 20000);

    this._success.subscribe(message => this.successMessage = message);
    this._success.pipe(
      debounceTime(2500)
    ).subscribe(() => this.successMessage = '');

    this.userService.getCurrentUser().pipe(first()).subscribe(data => {
      this.user = data;
      this.setForm();
    });
  }

  setForm() {
    this.editForm = this.formBuilder.group({
      username: [this.user.username, Validators.required],
      firstName: [this.user.firstName, Validators.required],
      lastName: [this.user.lastName, Validators.required],
      email: [this.user.email, [Validators.required, Validators.email]],
      password: [this.user.password, Validators.required],
    });
  }

  onSubmit(){
    let editedUser = new User(this.editForm.value);
    this.userService.update(editedUser).pipe(first()).subscribe(data => {
      this.user = data;
      this.setForm();
      this._success.next(`Update Success !`);
    });
  }

}
