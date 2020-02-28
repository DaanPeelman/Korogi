import { TestBed } from '@angular/core/testing';

import { AuthenticatedGuardGuard } from './authenticated-guard.guard';
import { CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { RouterTestingModule } from "@angular/router/testing";
import { KeycloakService } from "keycloak-angular";
import { instance, mock } from "ts-mockito";

describe('AuthenticatedGuardGuard', () => {
  let guard: AuthenticatedGuardGuard;

  let keycloakServiceMock: KeycloakService = mock(KeycloakService);

  beforeEach(() => {
    TestBed.configureTestingModule({
        schemas: [
            CUSTOM_ELEMENTS_SCHEMA
        ],
        declarations: [],
        imports: [
            RouterTestingModule
        ],
        providers: [
            {
                provide: KeycloakService,
                useFactory: () => instance(keycloakServiceMock)
            }
        ]
    });
    guard = TestBed.inject(AuthenticatedGuardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
