package com.korogi.rest.service;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;
import com.korogi.api.AnimeRestService;
import com.korogi.core.domain.Anime;
import com.korogi.core.persistence.anime.AnimeRepository;
import com.korogi.dto.AnimeDTO;
import com.korogi.rest.exception.ResourceNotFoundException;
import com.korogi.rest.mapper.EntityToDTOResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/anime", produces = { MediaType.APPLICATION_JSON_VALUE })
public class AnimeRestServiceImpl implements AnimeRestService {
    private static final String PATH_VARIABLE_ID = "id";

    private final AnimeRepository animeRepository;
    private final EntityToDTOResourceMapper entityToDTOResourceMapper;

    @Autowired
    public AnimeRestServiceImpl(
            AnimeRepository animeRepository,
            EntityToDTOResourceMapper entityToDTOResourceMapper
    ) {
        this.animeRepository = animeRepository;
        this.entityToDTOResourceMapper = entityToDTOResourceMapper;
    }

    @RequestMapping(method = GET)
    @ResponseStatus(OK)
    @Override
    public @ResponseBody PagedResources<Resource<AnimeDTO>> getAnime() {
        List<Anime> anime = animeRepository.findByCriteria();
        return entityToDTOResourceMapper.toPagedResources(anime, 1L, 20L);
    }

    @RequestMapping(value = "{" + PATH_VARIABLE_ID + "}", method = GET)
    @ResponseStatus(OK)
    @Override
    public @ResponseBody Resource<AnimeDTO> getAnimeDetails(
            @PathVariable(PATH_VARIABLE_ID) Long id
    ) {
        Anime anime = animeRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return entityToDTOResourceMapper.toDTOResource(anime);
    }

    @RequestMapping(value = "{" + PATH_VARIABLE_ID + "}/prequal", method = GET)
    @ResponseStatus(OK)
    @Override
    public Resource<AnimeDTO> getPrequalDetails(
            @PathVariable(PATH_VARIABLE_ID) Long id
    ) {
        Anime anime = animeRepository.findPrequalOfAnime(id).orElseThrow(ResourceNotFoundException::new);
        return entityToDTOResourceMapper.toDTOResource(anime);
    }

    @RequestMapping(value = "{" + PATH_VARIABLE_ID + "}/sequal", method = GET)
    @ResponseStatus(OK)
    @Override
    public Resource<AnimeDTO> getSequalDetails(
            @PathVariable(PATH_VARIABLE_ID) Long id
    ) {
        Anime anime = animeRepository.findSequalOfAnime(id).orElseThrow(ResourceNotFoundException::new);
        return entityToDTOResourceMapper.toDTOResource(anime);
    }
}
