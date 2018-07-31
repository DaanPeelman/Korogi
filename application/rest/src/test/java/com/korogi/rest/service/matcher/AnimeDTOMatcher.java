package com.korogi.rest.service.matcher;

import com.korogi.dto.AnimeDTO;

class AnimeDTOMatcher extends DTOMatchers.BaseDTOMatcher<AnimeDTO> {

    public AnimeDTOMatcher(AnimeDTO expected) {
        super(expected, AnimeDTO.class);
    }

    @Override
    public boolean matches(Object item) {
        return expected.equals(parseToObject(item));
    }
}