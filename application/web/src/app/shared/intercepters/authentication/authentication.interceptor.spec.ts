import { TestBed } from "@angular/core/testing";

import { AuthenticationInterceptor } from "./authentication.interceptor";
import { HttpClientTestingModule } from "@angular/common/http/testing";

describe("AuthenticationHeaderInterceptor", () => {
    beforeEach(() => TestBed.configureTestingModule(
        {
            imports: [
                HttpClientTestingModule
            ],
            providers: [
                AuthenticationInterceptor
            ]
        }
    ));

    it("should be created", () => {
        const interceptor: AuthenticationInterceptor = TestBed.inject(AuthenticationInterceptor);
        expect(interceptor).toBeTruthy();
    });
});
