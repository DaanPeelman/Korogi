export abstract class BaseRestService {
  protected baseRestEndPoint: string;

  constructor(restEndPoint: string) {
    this.baseRestEndPoint = "/rest/" + restEndPoint;
  }
}
