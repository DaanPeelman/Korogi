export class Anime {
  /**
   * Creates an instance of an <code>Anime</code> class from a given object.
   *
   * @param toCopy the object to create an Anime instance of
   * @returns {Anime} the newly constructed Anime instance based on the object that was passed
   */
  static toAnime(toCopy: any): Anime {
    return new Anime(
      toCopy.nameEnglish,
      toCopy.nameRomanized,
      toCopy.startAir,
      toCopy.endAir,
      toCopy.synopsis,
      toCopy.backdropUrl,
      toCopy.posterUrl
    );
  }

  /**
   * Creates instances of <code>Anime</code> classes from given objects.
   *
   * @param {any[]} toCopy the objects to create Anime instances of
   * @returns {Anime[]} an array containing newly constructed Anime instances based on the objects that were passed
   */
  static toAnimes(toCopy: any[]): Anime[] {
    const copied: Anime[] = [];

    for (const objectToCopy of toCopy) {
      copied.push(Anime.toAnime(objectToCopy));
    }

    return copied;
  }

  constructor(
    public nameEnglish: string,
    public nameRomanized: string,
    public startAir: any,
    public endAir: any,
    public synopsis: string,
    public backdropUrl: string,
    public posterUrl: string
  ) {}
}
