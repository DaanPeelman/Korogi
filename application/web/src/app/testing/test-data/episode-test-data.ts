import { EpisodeDTO } from "../../shared/models/episode-dto";
import { DateUtil } from "../../shared/utils/date-util";

export class EpisodeTestData {
    static steinsGate_episode1(): EpisodeDTO {
        let episodeDTO: EpisodeDTO = new EpisodeDTO();

        episodeDTO.name = "Prologue of the Beginning and Ending";
        episodeDTO.synopsis = "Steins Gate episode 1 synopsis";
        episodeDTO.durationInMinutes = 24;
        episodeDTO.airDate = DateUtil.parse("2011-04-05");

        return episodeDTO;
    }

    static steinsGate_episode2(): EpisodeDTO {
        let episodeDTO: EpisodeDTO = new EpisodeDTO();

        episodeDTO.name = "Paranoia of Time Leaps";
        episodeDTO.synopsis = "Steins Gate episode 2 synopsis";
        episodeDTO.durationInMinutes = 24;
        episodeDTO.airDate = DateUtil.parse("2011-04-12");

        return episodeDTO;
    }
}
