import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { LoginCallbackComponent } from "./login-callback.component";

@NgModule(
    {
        imports: [
            CommonModule
        ],
        declarations: [LoginCallbackComponent],
        exports: [LoginCallbackComponent]
    }
)
export class LoginCallbackModule {
}
