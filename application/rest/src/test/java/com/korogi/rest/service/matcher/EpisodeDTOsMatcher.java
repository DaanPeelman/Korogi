package com.korogi.rest.service.matcher;

import java.util.Arrays;
import com.korogi.dto.EpisodeDTO;

public class EpisodeDTOsMatcher extends DTOMatchers.BaseDTOMatcher<EpisodeDTO[]> {
    public EpisodeDTOsMatcher(EpisodeDTO[] expected) {
        super(expected, EpisodeDTO[].class);
    }

    @Override
    public boolean matches(Object item) {
        return Arrays.equals(expected, parseToObject(item));
    }
}