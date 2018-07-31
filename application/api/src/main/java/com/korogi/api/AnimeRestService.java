package com.korogi.api;

import com.korogi.dto.AnimeDTO;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;

public interface AnimeRestService {
    PagedResources<Resource<AnimeDTO>> getAnime();
    Resource<AnimeDTO> getAnimeDetails(Long id);
    Resource<AnimeDTO> getPrequalDetails(Long id);
    Resource<AnimeDTO> getSequalDetails(Long id);
}
