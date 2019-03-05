import { EpisodeDTO } from "../../models/episode-dto";
import { BaseMapper } from "../base-mapper";

export class EpisodeMapper extends BaseMapper<EpisodeDTO> {
    protected getObjectInstance(): EpisodeDTO {
        return new EpisodeDTO();
    }

    forType(): string {
        return "episode";
    }
}
