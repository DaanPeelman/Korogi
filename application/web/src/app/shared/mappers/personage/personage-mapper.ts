import { IMapper } from "../model-mapper.service";
import { PersonageDTO } from "../../models/personage-dto";

export class PersonageMapper implements IMapper<PersonageDTO> {
    private convert(resource: any): PersonageDTO {
        return Object.assign(
            Object.create(PersonageDTO.prototype),
            resource
        );
    }

    map(resource: any): PersonageDTO {
        let personageDTO: PersonageDTO = this.convert(resource);
        delete personageDTO["type"];

        return personageDTO;
    }
}
