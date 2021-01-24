package com.korogi.rest.restservice;

import static lombok.AccessLevel.PUBLIC;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.korogi.api.EpisodeRestService;
import com.korogi.core.domain.Episode;
import com.korogi.core.persistence.episode.EpisodeRepository;
import com.korogi.dto.EpisodeDTO;
import com.korogi.rest.exception.ResourceNotFoundException;
import com.korogi.rest.mapper.EntityToDTOResourceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/episodes", produces = { APPLICATION_JSON_VALUE })
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = PUBLIC)
public class EpisodeRestServiceImpl implements EpisodeRestService {
    private static final String PATH_VARIABLE_ID = "id";

    private final EpisodeRepository episodeRepository;
    private final EntityToDTOResourceMapper entityToDTOResourceMapper;

    @GetMapping
    @ResponseStatus(OK)
    @Override
    public @ResponseBody PagedModel<EntityModel<EpisodeDTO>> getEpisodes() {
        return null;
    }

    @GetMapping(value = "{" + PATH_VARIABLE_ID + "}")
    @ResponseStatus(OK)
    @Override
    public @ResponseBody EntityModel<EpisodeDTO> getEpisodeDetails(
            @PathVariable(PATH_VARIABLE_ID) Long id
    ) {
        Episode episode = episodeRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return entityToDTOResourceMapper.toDTOResource(episode);
    }
}