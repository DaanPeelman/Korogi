import { Anime } from "../shared/models/anime";

export class AnimeMother {
  static steinsGate(): Anime {
    return new Anime(
      'Steins;Gate',
      'Steins;Gate',
      '2011-04-06',
      '2011-09-14',
      'The self-proclaimed mad scientist Rintarou Okabe rents out a room in a rickety old building in Akihabara, where he indulges himself in his hobby of inventing prospective \'future gadgets\' with fellow lab members: Mayuri Shiina, his air-headed childhood friend, and Hashida Itaru, a perverted hacker nicknamed \'Daru.\' The three pass the time by tinkering with their most promising contraption yet, a machine dubbed the \'Phone Microwave,\' which performs the strange function of morphing bananas into piles of green gel.',
      'https://localhost:8443/assets/images/backdrop.jpg',
      'https://localhost:8443/assets/images/poster.jpg');
  }
}
