package com.korogi.api;

import java.util.List;
import com.korogi.api.dto.AnimeDTO;

public interface AnimeRestService {
    List<AnimeDTO> getAnime();
    AnimeDTO getAnime(String id);
}
