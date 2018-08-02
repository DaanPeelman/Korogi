import { IMapper } from "../model-mapper.service";
import { Anime } from "../../models/anime";

export class AnimeMapper implements IMapper<Anime> {
  map(resource: any): Anime {
    return new Anime(
      resource.nameEnglish,
      resource.nameRomanized,
      resource.startAir,
      resource.endAir,
      resource.synopsis,
      resource.backdropUrl,
      resource.posterUrl
    );
  }
}
