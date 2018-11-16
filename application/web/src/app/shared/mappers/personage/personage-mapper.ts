import { IMapper } from "../model-mapper.service";
import { PersonageDTO } from "../../../generated/models";

export class PersonageMapper implements IMapper<PersonageDTO> {
  map(resource: any): PersonageDTO {
    let personageDTO: PersonageDTO = new PersonageDTO();

    personageDTO.firstName = resource.firstName;
    personageDTO.lastName = resource.lastName;
    personageDTO.photoUrl = resource.photoUrl;

    return personageDTO;
  }
}
