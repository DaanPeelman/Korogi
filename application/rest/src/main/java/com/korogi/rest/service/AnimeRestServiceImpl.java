package com.korogi.rest.service;

import static com.korogi.dto.AnimeDTO.newAnimeDTO;
import static java.time.Month.APRIL;
import static java.time.Month.SEPTEMBER;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import com.korogi.api.AnimeRestService;
import com.korogi.api.hateoas.EmbeddedResource;
import com.korogi.dto.AnimeDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/anime", produces = { MediaType.APPLICATION_JSON_VALUE })
public class AnimeRestServiceImpl implements AnimeRestService {
    private static final String PATH_VARIABLE_ID = "id";
    private static final String PARAM_EMBEDS = "embed";

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
    public @ResponseBody PagedResources<EmbeddedResource<AnimeDTO>> getAnime(
            @RequestParam(value = PARAM_EMBEDS, required = false, defaultValue = "") List<String> embeds
    ) {
        PageMetadata metadata = new PageMetadata(10L, 1L, 20L);
        return new PagedResources<>(Collections.singletonList(toEmbeddedResource(ANIME_1, 1L, embeds)), metadata);
    }

    @RequestMapping(value = "{" + PATH_VARIABLE_ID + "}", method = GET)
    @Override
    public @ResponseBody EmbeddedResource<AnimeDTO> getAnime(
            @PathVariable(PATH_VARIABLE_ID) Long id,
            @RequestParam(value = PARAM_EMBEDS, required = false, defaultValue = "") List<String> embeds
    ) {
       return toEmbeddedResource(ANIME_1, 1L, embeds);
    }

    private EmbeddedResource<AnimeDTO> toEmbeddedResource(AnimeDTO anime, Long id, List<String> embeds) {
        Link selfLink = linkTo(methodOn(AnimeRestServiceImpl.class).getAnime(id, embeds)).withSelfRel();
        return new EmbeddedResource<>(anime, selfLink);
    }
}
