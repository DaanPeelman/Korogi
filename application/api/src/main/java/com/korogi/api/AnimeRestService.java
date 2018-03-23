package com.korogi.api;

import java.util.List;
import com.korogi.api.hateoas.EmbeddedResource;
import com.korogi.dto.AnimeDTO;
import org.springframework.hateoas.PagedResources;

public interface AnimeRestService {
    PagedResources<EmbeddedResource<AnimeDTO>> getAnime(List<String> embeds);
    EmbeddedResource<AnimeDTO> getAnime(Long id, List<String> embeds);
}
