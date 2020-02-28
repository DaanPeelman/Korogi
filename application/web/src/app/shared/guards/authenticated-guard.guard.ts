import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { KeycloakAuthGuard, KeycloakService } from "keycloak-angular";

@Injectable({
  providedIn: 'root'
})
export class AuthenticatedGuardGuard extends KeycloakAuthGuard implements CanActivate {

    constructor(protected router: Router, protected keycloakAngular: KeycloakService) {
        super(router, keycloakAngular);
    }

    isAccessAllowed(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean | UrlTree> {

        return new Promise((resolve, reject) => {
            if (!this.authenticated) {
                this.keycloakAngular.login()
                    .catch(e => console.error(e));
                return reject(false);
            } else {
                return resolve(true);
            }
        });
    }
}
