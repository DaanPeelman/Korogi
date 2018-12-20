package com.korogi.rest.service;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.korogi.api.EpisodeRestService;
import com.korogi.core.domain.Episode;
import com.korogi.core.persistence.episode.EpisodeRepository;
import com.korogi.dto.EpisodeDTO;
import com.korogi.rest.exception.ResourceNotFoundException;
import com.korogi.rest.mapper.EntityToDTOResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/episodes", produces = { APPLICATION_JSON_VALUE })
@Transactional(readOnly = true)
public class EpisodeRestServiceImpl implements EpisodeRestService {
    private static final String PATH_VARIABLE_ID = "id";

    private final EpisodeRepository episodeRepository;
    private final EntityToDTOResourceMapper entityToDTOResourceMapper;

    @Autowired
    public EpisodeRestServiceImpl(
            EpisodeRepository episodeRepository,
            EntityToDTOResourceMapper entityToDTOResourceMapper
    ) {
        this.episodeRepository = episodeRepository;
        this.entityToDTOResourceMapper = entityToDTOResourceMapper;
    }

    @RequestMapping(method = GET)
    @ResponseStatus(OK)
    @Override
    public @ResponseBody PagedResources<Resource<EpisodeDTO>> getEpisodes() {
        return null;
    }

    @RequestMapping(value = "{" + PATH_VARIABLE_ID + "}", method = GET)
    @ResponseStatus(OK)
    @Override
    public @ResponseBody Resource<EpisodeDTO> getEpisodeDetails(
            @PathVariable(PATH_VARIABLE_ID) Long id
    ) {
        Episode episode = episodeRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return entityToDTOResourceMapper.toDTOResource(episode);
    }
}