package com.korogi.api;

import com.korogi.dto.EpisodeDTO;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;

public interface EpisodeRestService {
    PagedResources<Resource<EpisodeDTO>> getEpisodes();
    Resource<EpisodeDTO> getEpisodeDetails(Long id);
}