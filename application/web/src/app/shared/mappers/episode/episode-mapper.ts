import { IMapper } from "../model-mapper.service";
import { EpisodeDTO } from "../../../generated/models";

export class EpisodeMapper implements IMapper<EpisodeDTO> {
  map(resource: any): EpisodeDTO {
    let episodeDTO: EpisodeDTO = new EpisodeDTO();

    episodeDTO.name = resource.name;
    episodeDTO.synopsis = resource.synopsis;
    episodeDTO.durationInMinutes = resource.durationInMinutes;
    episodeDTO.airDate = resource.airDate;

    return episodeDTO;
  }
}
