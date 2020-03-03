import { Routes } from '@angular/router';

import { LoginComponent } from '../../pages/login/login.component';
import { RegisterComponent } from '../../pages/register/register.component';
import {HomeComponent} from "../../pages/home/home.component";
import {MobileAppComponent} from "../../pages/mobile-app/mobile-app.component";
import {TeamComponent} from "../../pages/team/team.component";

export const AuthLayoutRoutes: Routes = [
    { path: 'login',          component: LoginComponent },
    { path: 'home',          component: HomeComponent },
    { path: 'mobileApp',          component: MobileAppComponent },
    { path: 'team',          component: TeamComponent },
    { path: 'register',       component: RegisterComponent }
];
