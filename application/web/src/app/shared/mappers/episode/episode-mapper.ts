import { IMapper } from "../model-mapper.service";
import { EpisodeDTO } from "../../models/episode-dto";

export class EpisodeMapper implements IMapper<EpisodeDTO> {
    private convert(resource: any): EpisodeDTO {
        return Object.assign(
            new EpisodeDTO(),
            resource
        );
    }

    map(resource: any): EpisodeDTO {
        let episodeDTO: EpisodeDTO = this.convert(resource);
        delete episodeDTO["type"];

        return episodeDTO;
    }
}
