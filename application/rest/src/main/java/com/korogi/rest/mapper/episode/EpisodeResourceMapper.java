package com.korogi.rest.mapper.episode;

import static com.korogi.rest.mapper.BaseResourceMapper.COMPONENT_MODEL;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import com.korogi.core.domain.Episode;
import com.korogi.dto.EpisodeDTO;
import com.korogi.rest.mapper.BaseResourceMapper;
import com.korogi.rest.service.EpisodeRestServiceImpl;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;

@Mapper(componentModel = COMPONENT_MODEL)
abstract class EpisodeResourceMapper extends BaseResourceMapper<EpisodeDTO, Episode> {
    @Autowired
    public EpisodeResourceMapper() {
        super(Episode.class);
    }

    @Override
    protected List<Link> createLinks(Episode episode) {
        List<Link> links = new ArrayList<>();

        links.add(linkTo(methodOn(EpisodeRestServiceImpl.class).getEpisodeDetails(episode.getId())).withSelfRel());

        return links;
    }
}