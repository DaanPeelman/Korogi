package com.korogi.api;

import com.korogi.dto.AnimeDTO;
import com.korogi.dto.EpisodeDTO;
import com.korogi.dto.PersonageDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

public interface AnimeRestService {
    PagedModel<EntityModel<AnimeDTO>> getAnime();

    EntityModel<AnimeDTO> getAnimeDetails(Long id);

    EntityModel<AnimeDTO> getPrequalDetails(Long id);

    EntityModel<AnimeDTO> getSequalDetails(Long id);

    PagedModel<EntityModel<EpisodeDTO>> getAnimeEpisodes(Long id);

    PagedModel<EntityModel<PersonageDTO>> getAnimePersonages(Long id);
}
