import { PersonageDTO } from "../../generated/models";

export class PersonageTestData {
  static okabeRintarou(): PersonageDTO {
    let personageDTO: PersonageDTO = new PersonageDTO();

    personageDTO.firstName = "Okabe";
    personageDTO.lastName = "Rintarou";
    personageDTO.photoUrl = "http://photo.url.com/okabe.rintarou";

    return personageDTO;
  }

  static makiseKurisu(): PersonageDTO {
    let personageDTO: PersonageDTO = new PersonageDTO();

    personageDTO.firstName = "Makise";
    personageDTO.lastName = "Kurisu";
    personageDTO.photoUrl = "http://photo.url.com/makise.kurisu";

    return personageDTO;
  }
}
