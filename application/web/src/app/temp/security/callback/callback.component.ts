import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { SecurityService } from "../security.service";

@Component({
  selector: 'korogi-callback',
  templateUrl: './callback.component.html',
  styleUrls: ['./callback.component.scss']
})
export class CallbackComponent implements OnInit {

  constructor(private route: ActivatedRoute, private router: Router, private securityService: SecurityService) { }

  ngOnInit(): void {
      console.log("in callback");
      this.route.queryParams.subscribe(p => {
          console.log("fetching token")
          this.securityService.fetchToken(p.code, p.state).subscribe(data => {
              console.log("updating token");
              this.securityService.updateToken(data.accessToken);
              this.router.navigate(['/anime/1']);
          })
      })
  }

}
