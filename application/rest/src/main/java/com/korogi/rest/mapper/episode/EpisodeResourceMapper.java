package com.korogi.rest.mapper.episode;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.korogi.core.domain.Episode;
import com.korogi.dto.EpisodeDTO;
import com.korogi.rest.mapper.ResourceMapper;
import com.korogi.rest.service.EpisodeRestServiceImpl;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

@Component
class EpisodeResourceMapper implements ResourceMapper<EpisodeDTO, Episode> {
    @Override
    public Resource<EpisodeDTO> toDTOResource(Episode episode) {
        Link selfLink = linkTo(methodOn(EpisodeRestServiceImpl.class).getEpisodeDetails(episode.getId())).withSelfRel();

        return new Resource<>(episode.toDTO(), selfLink);
    }

    @Override
    public Class<Episode> fromObjectClass() {
        return Episode.class;
    }
}