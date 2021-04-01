import { ComponentFixture, inject, TestBed } from "@angular/core/testing";

import { LoginCallbackComponent } from "./login-callback.component";
import { ActivatedRoute } from "@angular/router";
import { StubUtil } from "../../testing/util/stub-util";
import { RouterTestingModule } from "@angular/router/testing";
import { AuthenticationService } from "../../shared/services/authentication/authentication-service";
import { instance, mock } from "ts-mockito";
import { UserService } from "../../shared/services/user/user.service";

describe("LoginCallbackComponent", () => {
    let component: LoginCallbackComponent;
    let fixture: ComponentFixture<LoginCallbackComponent>;

    let activatedRouteStub;
    const authenticationServiceMock: AuthenticationService = mock(AuthenticationService);
    const userServiceMock: UserService = mock(UserService);

    beforeEach(async () => {
        await TestBed.configureTestingModule(
            {
                declarations: [LoginCallbackComponent],
                imports: [
                    RouterTestingModule
                ],
                providers: [
                    {
                        provide: ActivatedRoute,
                        useFactory: () => StubUtil.subActivatedRoute()
                    },
                    {
                        provide: AuthenticationService,
                        useFactory: () => instance(authenticationServiceMock)
                    },
                    {
                        provide: UserService,
                        useFactory: () => instance(userServiceMock)
                    }
                ]
            }
        ).compileComponents();
    });

    beforeEach(inject([ActivatedRoute], (_activatedRouteStub: ActivatedRoute) => {
        activatedRouteStub = _activatedRouteStub;
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(LoginCallbackComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it("should create", () => {
        expect(component).toBeTruthy();
    });
});
