import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthLayoutRoutes } from './auth-layout.routing';

import { LoginComponent } from '../../pages/login/login.component';
import { RegisterComponent } from '../../pages/register/register.component';
import {HomeComponent} from "../../pages/home/home.component";
import {TeamComponent} from "../../pages/team/team.component";
import {MobileAppComponent} from "../../pages/mobile-app/mobile-app.component";
@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AuthLayoutRoutes),
    FormsModule,
    ReactiveFormsModule,
    // NgbModule
  ],
  declarations: [
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    TeamComponent,
    MobileAppComponent
  ]
})
export class AuthLayoutModule { }
