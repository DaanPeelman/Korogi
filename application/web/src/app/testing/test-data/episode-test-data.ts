import { Episode } from "../../shared/models/episode";

export class EpisodeTestData {
  static steinsGate_episode1(): Episode {
    return new Episode(
      "Prologue of the Beginning and Ending",
      "Steins Gate episode 1 synopsis",
      24,
      "2011-04-05"
    );
  }

  static steinsGate_episode2(): Episode {
    return new Episode(
      "Paranoia of Time Leaps",
      "Steins Gate episode 2 synopsis",
      24,
      "2011-04-12"
    );
  }
}
