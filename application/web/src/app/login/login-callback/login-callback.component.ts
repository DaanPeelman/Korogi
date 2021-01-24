import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { AuthenticationService } from "../../shared/services/authentication/authentication-service";
import { UserService } from "../../shared/services/user/user.service";
import { BaseUserDTO } from "../../generated/models";

@Component({
  selector: 'korogi-login-callback',
  templateUrl: './login-callback.component.html',
  styleUrls: ['./login-callback.component.scss']
})
export class LoginCallbackComponent implements OnInit {

  constructor(
      private route: ActivatedRoute,
      private router: Router,
      private authenticationService: AuthenticationService,
      private userService: UserService
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(p => {

      this.authenticationService.updateToken(p["token"]);
      this.userService.getCurrentUser().subscribe(data => {
        console.log("done!");
      }, error => {
        if (error.status === 404) {
          let user: BaseUserDTO = new BaseUserDTO();
          user.username = "Grumn"
          user.biography = "Some biography blblblbl";

          this.userService.registerUser(user).subscribe(() => {
            console.log("created user");
          });
        }
      });
    })
  }

}
