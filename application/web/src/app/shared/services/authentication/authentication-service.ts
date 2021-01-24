import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private static AUTHORIZE_ENDPOINT_GOOGLE = 'rest/oauth2/authorization/google';
  private static TOKEN_ENDPOINT = 'rest/login/oauth2/code/google';

  constructor(
      private httpClient: HttpClient
  ) { }

  public googleLogin(): void {
    window.open(window.location.origin + '/' + AuthenticationService.AUTHORIZE_ENDPOINT_GOOGLE, "_self");
  }

  public updateToken(token): void {
    localStorage.setItem("token", token);
  }

  public fetchToken(code, state) {
    console.log("open window to " + window.location.origin + '/' + AuthenticationService.TOKEN_ENDPOINT + "?code=" + code + "&state=" + state);
    window.open(window.location.origin + '/' + AuthenticationService.TOKEN_ENDPOINT + "?code=" + code + "&state=" + state);
    // return this.httpClient.get(AuthenticationService.TOKEN_ENDPOINT + '?code=' + code + '&state=' + state);
  }

  public getToken() {
    return localStorage.getItem("token");
  }

  public isLoggedIn(): boolean {
    return this.getToken() != null;
  }
}
