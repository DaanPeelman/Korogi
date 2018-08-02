package com.korogi.rest.mapper.anime;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import com.korogi.core.domain.Anime;
import com.korogi.dto.AnimeDTO;
import com.korogi.rest.mapper.ResourceMapper;
import com.korogi.rest.service.AnimeRestServiceImpl;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

@Component
class AnimeResourceMapper implements ResourceMapper<AnimeDTO, Anime> {
    @Override
    public Resource<AnimeDTO> toDTOResource(Anime anime) {
        List<Link> links = new ArrayList<>();

        links.add(linkTo(methodOn(AnimeRestServiceImpl.class).getAnimeDetails(anime.getId())).withSelfRel());
        if (anime.hasPrequal()) {
            links.add(linkTo(methodOn(AnimeRestServiceImpl.class).getPrequalDetails(anime.getId())).withRel("prequal"));
        }
        if (anime.hasSequal()) {
            links.add(linkTo(methodOn(AnimeRestServiceImpl.class).getSequalDetails(anime.getId())).withRel("sequal"));
        }
        links.add(linkTo(methodOn(AnimeRestServiceImpl.class).getAnimeEpisodes(anime.getId())).withRel("episodes"));
        links.add(linkTo(methodOn(AnimeRestServiceImpl.class).getAnimePersonages(anime.getId())).withRel("personages"));

        return new Resource<>(anime.toDTO(), links);
    }

    @Override
    public Class<Anime> fromObjectClass() {
        return Anime.class;
    }
}