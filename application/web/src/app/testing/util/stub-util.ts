import { ReplaySubject } from "rxjs/ReplaySubject";
import { ParamMap } from "@angular/router";

export class StubUtil {
    static subActivatedRoute(): any {
        return {
            paramMap: new ReplaySubject<ParamMap>(),
            queryParams: new ReplaySubject<any>()
        };
    }
}
