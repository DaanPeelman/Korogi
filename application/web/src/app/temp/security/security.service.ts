import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

    private authorizeEndpoint = 'rest/oauth2/authorization/google';
    private tokenEndpoint = 'rest/login/oauth2/code/google';

  constructor(private http: HttpClient) { }

  login() {
      window.open(window.location.origin + "/" + this.authorizeEndpoint, "_self");
  }

  updateToken(token) {
      localStorage.setItem("token", token);
  }

  fetchToken(code, state): Observable<any> {
      console.log(this.tokenEndpoint);
      return this.http.get(this.tokenEndpoint + "?code=" + code + "&state=" + state);
  }

  getToken() {
      return localStorage.getItem("token");
  }

  isLoggedIn() {
      return this.getToken() != null;
  }
}
