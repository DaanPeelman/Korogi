import { IMapper } from "../model-mapper.service";
import { AnimeDTO } from "../../../generated/models";

export class AnimeMapper implements IMapper<AnimeDTO> {
  map(resource: any): AnimeDTO {
    let animeDTO: AnimeDTO = new AnimeDTO();

    animeDTO.nameEnglish = resource.nameEnglish;
    animeDTO.nameRomanized = resource.nameRomanized;
    animeDTO.startAir = resource.startAir;
    animeDTO.endAir = resource.endAir;
    animeDTO.synopsis = resource.synopsis;
    animeDTO.backdropUrl = resource.backdropUrl;
    animeDTO.posterUrl = resource.posterUrl;

    return animeDTO;
  }
}
