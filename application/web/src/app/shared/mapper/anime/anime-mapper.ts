import { AnimeDTO } from "../../models/anime-dto";
import { BaseMapper } from "../base-mapper";
import { Injectable } from "@angular/core";

@Injectable()
export class AnimeMapper extends BaseMapper<AnimeDTO> {
    protected getObjectInstance(): AnimeDTO {
        return new AnimeDTO();
    }

    forType(): string {
        return "anime";
    }
}
