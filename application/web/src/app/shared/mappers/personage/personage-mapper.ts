import { IMapper } from "../model-mapper.service";
import { Personage } from "../../models/personage";

export class PersonageMapper implements IMapper<Personage> {
  map(resource: any): Personage {
    return new Personage(
      resource.firstName,
      resource.lastName,
      resource.photoUrl
    );
  }
}
