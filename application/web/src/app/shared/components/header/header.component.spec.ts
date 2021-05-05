import { ComponentFixture, TestBed, waitForAsync } from "@angular/core/testing";

import { HeaderComponent } from "./header.component";
import { CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { RouterTestingModule } from "@angular/router/testing";
import { instance, mock } from "ts-mockito";
import { AuthenticationService } from "../../services/authentication/authentication-service";

describe("HeaderComponent", () => {
    let component: HeaderComponent;
    let fixture: ComponentFixture<HeaderComponent>;

    const authenticationServiceMock: AuthenticationService = mock(AuthenticationService);

    beforeEach(waitForAsync(() => {
        TestBed.configureTestingModule(
            {
                schemas: [
                    CUSTOM_ELEMENTS_SCHEMA
                ],
                declarations: [
                    HeaderComponent
                ],
                imports: [
                    RouterTestingModule
                ],
                providers: [
                    {
                        provide: AuthenticationService,
                        useFactory: () => instance(authenticationServiceMock)
                    }
                ]
            }
        ).compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(HeaderComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it("should be created", () => {
        expect(component).toBeTruthy();
    });
});
