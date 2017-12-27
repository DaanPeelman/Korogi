package com.korogi.rest.services;

import static com.korogi.api.dto.AnimeDTO.newAnimeDTO;
import static java.time.Month.APRIL;
import static java.time.Month.SEPTEMBER;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import com.korogi.api.AnimeRestService;
import com.korogi.api.dto.AnimeDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/anime")
public class AnimeRestServiceImpl implements AnimeRestService {
    private static final String PATH_VARIABLE_ID = "id";

    private static final AnimeDTO ANIME_1 = newAnimeDTO()
            .nameEnglish("Steins;Gate")
            .nameRomanized("Steins;Gate")
            .startAir(LocalDate.of(2011, APRIL, 6))
            .endAir(LocalDate.of(2011, SEPTEMBER, 14))
            .synopsis("The self-proclaimed mad scientist Rintarou Okabe rents out a room in a rickety old building in Akihabara, where he indulges himself in his hobby of inventing prospective \"future gadgets\" with fellow lab members: Mayuri Shiina, his air-headed childhood friend, and Hashida Itaru, a perverted hacker nicknamed \"Daru.\" The three pass the time by tinkering with their most promising contraption yet, a machine dubbed the \"Phone Microwave,\" which performs the strange function of morphing bananas into piles of green gel.")
            .backdropUrl("https://localhost:8443/assets/images/backdrop.jpg")
            .posterUrl("https://localhost:8443/assets/poster.jpg")
            .build();

    @RequestMapping(method = GET)
    @Override
    public @ResponseBody List<AnimeDTO> getAnime() {
        return Arrays.asList(ANIME_1);
    }

    @RequestMapping(value = "{" + PATH_VARIABLE_ID + "}", method = GET)
    @Override
    public AnimeDTO getAnime(
            @PathVariable(PATH_VARIABLE_ID) String id
    ) {
        return ANIME_1;
    }
}
