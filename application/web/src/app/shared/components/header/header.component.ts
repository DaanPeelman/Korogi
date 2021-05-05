import { Component, OnInit } from "@angular/core";
import { AuthenticationService } from "../../services/authentication/authentication-service";

@Component(
    {
        selector: "korogi-header",
        templateUrl: "./header.component.html",
        styleUrls: ["./header.component.scss"]
    }
)
export class HeaderComponent implements OnInit {
    transparent: boolean;

    constructor(
        private authenticationService: AuthenticationService
    ) {
    }

    ngOnInit() {
        this.transparent = true;
    }

    public login() {
        this.authenticationService.googleLogin();
    }

}
