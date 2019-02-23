package com.korogi.rest.mapper.anime;

import static com.korogi.dto.AnimeDTO.AnimeRelation.EPISODES;
import static com.korogi.dto.AnimeDTO.AnimeRelation.PERSONAGES;
import static com.korogi.dto.AnimeDTO.AnimeRelation.PREQUAL;
import static com.korogi.dto.AnimeDTO.AnimeRelation.SEQUAL;
import static com.korogi.rest.mapper.BaseResourceMapper.COMPONENT_MODEL;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import com.korogi.core.domain.Anime;
import com.korogi.dto.AnimeDTO;
import com.korogi.rest.mapper.BaseResourceMapper;
import com.korogi.rest.service.AnimeRestServiceImpl;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;

@Mapper(componentModel = COMPONENT_MODEL)
abstract class AnimeResourceMapper extends BaseResourceMapper<AnimeDTO, Anime> {

    @Autowired
    public AnimeResourceMapper() {
        super(Anime.class);
    }

    @Override
    protected List<Link> createLinks(Anime anime) {
        List<Link> links = new ArrayList<>();

        links.add(linkTo(methodOn(AnimeRestServiceImpl.class).getAnimeDetails(anime.getId())).withSelfRel());
        links.add(linkTo(methodOn(AnimeRestServiceImpl.class).getPrequalDetails(anime.getId())).withRel(PREQUAL.getValue()));
        links.add(linkTo(methodOn(AnimeRestServiceImpl.class).getSequalDetails(anime.getId())).withRel(SEQUAL.getValue()));
        links.add(linkTo(methodOn(AnimeRestServiceImpl.class).getAnimeEpisodes(anime.getId())).withRel(EPISODES.getValue()));
        links.add(linkTo(methodOn(AnimeRestServiceImpl.class).getAnimePersonages(anime.getId())).withRel(PERSONAGES.getValue()));

        return links;
    }
}