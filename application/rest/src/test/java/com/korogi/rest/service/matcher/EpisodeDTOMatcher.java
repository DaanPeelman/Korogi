package com.korogi.rest.service.matcher;

import com.korogi.dto.EpisodeDTO;

public class EpisodeDTOMatcher extends DTOMatchers.BaseDTOMatcher<EpisodeDTO> {
    public EpisodeDTOMatcher(EpisodeDTO expected) {
        super(expected, EpisodeDTO.class);
    }

    @Override
    public boolean matches(Object item) {
        return expected.equals(parseToObject(item));
    }
}