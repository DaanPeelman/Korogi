export abstract class BaseRestService {
    protected baseRestEndPoint: string;

    protected constructor(restEndPoint: string) {
        this.baseRestEndPoint = "/rest/" + restEndPoint;
    }
}
