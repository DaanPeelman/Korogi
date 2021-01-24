import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from "@angular/router";
import { loginRoutes } from "./login.routes";
import { LoginCallbackModule } from "./login-callback/login-callback.module";

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(loginRoutes),

    LoginCallbackModule
  ],
  declarations: []
})
export class LoginModule { }
