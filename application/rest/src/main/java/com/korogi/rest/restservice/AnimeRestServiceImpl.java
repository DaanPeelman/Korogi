package com.korogi.rest.restservice;

import static com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils.fromAttributePaths;
import static com.korogi.core.domain.Anime_.EPISODES;
import static com.korogi.core.domain.Anime_.PERSONAGES;
import static lombok.AccessLevel.PUBLIC;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import com.korogi.api.AnimeRestService;
import com.korogi.core.domain.Anime;
import com.korogi.core.persistence.anime.AnimeRepository;
import com.korogi.dto.AnimeDTO;
import com.korogi.dto.EpisodeDTO;
import com.korogi.dto.PersonageDTO;
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
@RequestMapping(value = "/anime", produces = { APPLICATION_JSON_VALUE })
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = PUBLIC)
public class AnimeRestServiceImpl implements AnimeRestService {
    private static final String PATH_VARIABLE_ID = "id";

    private final AnimeRepository animeRepository;
    private final EntityToDTOResourceMapper entityToDTOResourceMapper;

    @GetMapping
    @ResponseStatus(OK)
    @Override
    public @ResponseBody PagedModel<EntityModel<AnimeDTO>> getAnime() {
        List<Anime> anime = animeRepository.findByCriteria();

        return entityToDTOResourceMapper.toPagedResources(anime, 1L, 20L);
    }

    @GetMapping(value = "{" + PATH_VARIABLE_ID + "}")
    @ResponseStatus(OK)
    @Override
    public @ResponseBody EntityModel<AnimeDTO> getAnimeDetails(
            @PathVariable(PATH_VARIABLE_ID) Long id
    ) {
        Anime anime = animeRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        return entityToDTOResourceMapper.toDTOResource(anime);
    }

    @GetMapping(value = "{" + PATH_VARIABLE_ID + "}/prequal")
    @ResponseStatus(OK)
    @Override
    public @ResponseBody EntityModel<AnimeDTO> getPrequalDetails(
            @PathVariable(PATH_VARIABLE_ID) Long id
    ) {
        Anime anime = animeRepository.findPrequalOfAnime(id).orElseThrow(ResourceNotFoundException::new);

        return entityToDTOResourceMapper.toDTOResource(anime);
    }

    @GetMapping(value = "{" + PATH_VARIABLE_ID + "}/sequal")
    @ResponseStatus(OK)
    @Override
    public @ResponseBody EntityModel<AnimeDTO> getSequalDetails(
            @PathVariable(PATH_VARIABLE_ID) Long id
    ) {
        Anime anime = animeRepository.findSequalOfAnime(id).orElseThrow(ResourceNotFoundException::new);

        return entityToDTOResourceMapper.toDTOResource(anime);
    }

    @GetMapping(value = "{" + PATH_VARIABLE_ID + "}/episodes")
    @ResponseStatus(OK)
    @Override
    public @ResponseBody PagedModel<EntityModel<EpisodeDTO>> getAnimeEpisodes(
            @PathVariable(PATH_VARIABLE_ID) Long id
    ) {
        Anime anime = animeRepository.findById(id, fromAttributePaths(EPISODES)).orElseThrow(ResourceNotFoundException::new);

        return entityToDTOResourceMapper.toPagedResources(anime.getEpisodes(), 1L, 20L);
    }

    @GetMapping(value = "{" + PATH_VARIABLE_ID + "}/personages")
    @ResponseStatus(OK)
    @Override
    public @ResponseBody PagedModel<EntityModel<PersonageDTO>> getAnimePersonages(
            @PathVariable(PATH_VARIABLE_ID) Long id
    ) {
        Anime anime = animeRepository.findById(id, fromAttributePaths(PERSONAGES)).orElseThrow(ResourceNotFoundException::new);

        return entityToDTOResourceMapper.toPagedResources(anime.getPersonages(), 1L, 20L);
    }
}
