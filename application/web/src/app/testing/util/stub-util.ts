import { ReplaySubject } from "rxjs/ReplaySubject";

export class StubUtil {
  static subActivatedRoute(): any {
    return {
      params: new ReplaySubject<any>()
    };
  }
}
