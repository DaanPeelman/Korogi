import { EpisodeDTO } from "../../models/episode-dto";
import { BaseMapper } from "../base-mapper";
import { Injectable } from "@angular/core";

@Injectable()
export class EpisodeMapper extends BaseMapper<EpisodeDTO> {
    forType(): string {
        return "episode";
    }

    protected getObjectInstance(): EpisodeDTO {
        return new EpisodeDTO();
    }
}
