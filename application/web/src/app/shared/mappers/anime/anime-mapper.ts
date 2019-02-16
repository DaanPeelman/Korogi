import { IMapper } from "../model-mapper.service";
import { AnimeDTO } from "../../models/anime-dto";

export class AnimeMapper implements IMapper<AnimeDTO> {
    private convert(resource: any): AnimeDTO {
        return Object.assign(
            new AnimeDTO(),
            resource
        );
    }

    map(resource: any): AnimeDTO {
        let animeDTO: AnimeDTO = this.convert(resource);
        delete animeDTO["type"];

        return animeDTO;
    }
}
