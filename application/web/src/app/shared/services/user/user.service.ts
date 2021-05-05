import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { BaseRestService } from "../base-rest-service";
import { BaseUserDTO } from "../../../generated/models";

@Injectable(
    {
        providedIn: "root"
    }
)
export class UserService extends BaseRestService {
    private static URL: string = "users";

    constructor(
        private httpClient: HttpClient
    ) {
        super(UserService.URL);
    }

    public getCurrentUser(): Observable<BaseUserDTO> {
        return this.httpClient.get<BaseUserDTO>(`${this.baseRestEndPoint}/current`);
    }

    public registerUser(user: BaseUserDTO): Observable<void> {
        return this.httpClient.post<void>(`${this.baseRestEndPoint}`, user);
    }
}
