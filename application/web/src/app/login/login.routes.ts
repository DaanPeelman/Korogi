import { Routes } from "@angular/router";
import { LOGIN_CALLBACK } from "../shared/constants/pages.constant";
import { LoginCallbackComponent } from "./login-callback/login-callback.component";

export const loginRoutes: Routes = [
    {
        path: LOGIN_CALLBACK,
        component: LoginCallbackComponent
    }
];
