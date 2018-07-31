package com.korogi.rest.mapper.anime;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
        Link selfLink = linkTo(methodOn(AnimeRestServiceImpl.class).getAnimeDetails(anime.getId())).withSelfRel();
        Link prequalLink = linkTo(methodOn(AnimeRestServiceImpl.class).getPrequalDetails(anime.getId())).withRel("prequal");
        Link sequalLink = linkTo(methodOn(AnimeRestServiceImpl.class).getSequalDetails(anime.getId())).withRel("sequal");

        return new Resource<>(anime.toDTO(), selfLink, prequalLink, sequalLink);
    }

    @Override
    public Class<Anime> fromObjectClass() {
        return Anime.class;
    }
}