package com.korogi.api;

import com.korogi.dto.EpisodeDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

public interface EpisodeRestService {
    PagedModel<EntityModel<EpisodeDTO>> getEpisodes();

    EntityModel<EpisodeDTO> getEpisodeDetails(Long id);
}