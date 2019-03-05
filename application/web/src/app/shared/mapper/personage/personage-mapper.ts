import { PersonageDTO } from "../../models/personage-dto";
import { BaseMapper } from "../base-mapper";
import { Injectable } from "@angular/core";

@Injectable()
export class PersonageMapper extends BaseMapper<PersonageDTO> {
    protected getObjectInstance(): PersonageDTO {
        return new PersonageDTO();
    }

    forType(): string {
        return "personage";
    }
}
