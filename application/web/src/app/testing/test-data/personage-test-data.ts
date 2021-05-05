import { PersonageDTO } from "../../shared/models/personage-dto";

export class PersonageTestData {
    static okabeRintarou(): PersonageDTO {
        const personageDTO: PersonageDTO = new PersonageDTO();

        personageDTO.firstName = "Okabe";
        personageDTO.lastName = "Rintarou";
        personageDTO.photoUrl = "http://photo.url.com/okabe.rintarou";

        return personageDTO;
    }

    static makiseKurisu(): PersonageDTO {
        const personageDTO: PersonageDTO = new PersonageDTO();

        personageDTO.firstName = "Makise";
        personageDTO.lastName = "Kurisu";
        personageDTO.photoUrl = "http://photo.url.com/makise.kurisu";

        return personageDTO;
    }
}
