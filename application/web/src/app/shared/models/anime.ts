export class Anime {
  static readonly RELATION_PREQUAL: string = "prequal";
  static readonly RELATION_SEQUAL: string = "sequal";
  static readonly RELATION_EPISODES: string = "episodes";
  static readonly RELATION_PERSONAGES: string = "personages";

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
