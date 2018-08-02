import { IMapper } from "../model-mapper.service";
import { Episode } from "../../models/episode";

export class EpisodeMapper implements IMapper<Episode> {
  map(resource: any): Episode {
    return new Episode(
      resource.name,
      resource.synopsis,
      resource.durationInMinutes,
      resource.airDate
    );
  }
}
