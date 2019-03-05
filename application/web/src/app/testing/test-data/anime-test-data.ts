import { AnimeDTO } from "../../shared/models/anime-dto";
import { DateUtil } from "../../shared/utils/date-util";

export class AnimeTestData {
    static steinsGate(): AnimeDTO {
        let animeDTO: AnimeDTO = new AnimeDTO();

        animeDTO.nameEnglish = 'Steins;Gate';
        animeDTO.nameRomanized = 'Steins;Gate';
        animeDTO.startAir = DateUtil.parse('2011-04-06');
        animeDTO.endAir = DateUtil.parse('2011-09-14');
        animeDTO.synopsis = 'The self-proclaimed mad scientist Rintarou Okabe rents out a room in a rickety old building in Akihabara, where he indulges himself in his hobby of inventing prospective \'future gadgets\' with fellow lab members: Mayuri Shiina, his air-headed childhood friend, and Hashida Itaru, a perverted hacker nicknamed \'Daru.\' The three pass the time by tinkering with their most promising contraption yet, a machine dubbed the \'Phone Microwave,\' which performs the strange function of morphing bananas into piles of green gel.';
        animeDTO.backdropUrl = 'https://localhost:8443/assets/images/backdrop.steins.gate.jpg';
        animeDTO.posterUrl = 'https://localhost:8443/assets/images/poster.steins.gate.jpg';

        return animeDTO;
    }

    static steinsGateEgoisticPoriomania(): AnimeDTO {
        let animeDTO: AnimeDTO = new AnimeDTO();

        animeDTO.nameEnglish = "Steins;Gate: Egoistic Poriomania";
        animeDTO.nameRomanized = "Steins;Gate: Oukoubakko no Poriomania";
        animeDTO.startAir = DateUtil.parse("2012-02-22");
        animeDTO.endAir = DateUtil.parse("2012-02-22");
        animeDTO.synopsis = "Special episode included in the last of the nine Blu-ray/DVD volumes. The story is set a few months after the last scene of the television series. On a calm day in Akihabara, Okabe and the other laboratory members are invited by Feyris to go to America. Mayuri and the rest are planning to reunite with Kurisu, who had come back to an American laboratory. A few days later, Okabe's mad scientist persona Hououin Kyouma arrives alone at the airport in Los Angeles, where he may be taking the first step to leading the world into chaos.";
        animeDTO.backdropUrl = 'https://localhost:8443/assets/images/backdrop.steins.gate.egoistic.poriomania.jpg';
        animeDTO.posterUrl = 'https://localhost:8443/assets/images/poster.steins.gate.egoistic.poriomania.jpg';

        return animeDTO;
    }

    static naruto(): AnimeDTO {
        let animeDTO: AnimeDTO = new AnimeDTO();

        animeDTO.nameEnglish = "Naruto";
        animeDTO.nameRomanized = "Naruto";
        animeDTO.startAir = DateUtil.parse("2002-10-03");
        animeDTO.endAir = DateUtil.parse("2007-02-08");
        animeDTO.synopsis = "Moments prior to Naruto Uzumaki's birth, a huge demon known as the Kyuubi, the Nine-Tailed Fox, attacked Konohagakure, the Hidden Leaf Village, and wreaked havoc. In order to put an end to the Kyuubi's rampage, the leader of the village, the Fourth Hokage, sacrificed his life and sealed the monstrous beast inside the newborn Naruto. Now, Naruto is a hyperactive and knuckle-headed ninja still living in Konohagakure. Shunned because of the Kyuubi inside him, Naruto struggles to find his place in the village, while his burning desire to become the Hokage of Konohagakure leads him not only to some great new friends, but also some deadly foes.";
        animeDTO.backdropUrl = "https://localhost:8443/assets/images/backdrop.naruto.jpg";
        animeDTO.posterUrl = "https://localhost:8443/assets/images/poster.naruto.jpg";

        return animeDTO;
    }
}
