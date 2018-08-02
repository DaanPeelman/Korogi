import { Personage } from "../../shared/models/personage";

export class PersonageTestData {
  static okabeRintarou(): Personage {
    return new Personage(
      "Okabe",
      "Rintarou",
      "http://photo.url.com/okabe.rintarou"
    );
  }

  static makiseKurisu(): Personage {
    return new Personage(
      "Makise",
      "Kurisu",
      "http://photo.url.com/makise.kurisu"
    )
  }
}
