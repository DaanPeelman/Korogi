import { AnimeDTO } from "../../models/anime-dto";
import { BaseMapper } from "../base-mapper";

export class AnimeMapper extends BaseMapper<AnimeDTO> {
    protected getObjectInstance(): AnimeDTO {
        return new AnimeDTO();
    }

    forType(): string {
        return "anime";
    }
}
