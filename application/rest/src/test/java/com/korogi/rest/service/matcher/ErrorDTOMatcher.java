package com.korogi.rest.service.matcher;

import com.korogi.dto.ErrorDTO;

public class ErrorDTOMatcher extends DTOMatchers.BaseDTOMatcher<ErrorDTO> {
    public ErrorDTOMatcher(ErrorDTO expected) {
        super(expected, ErrorDTO.class);
    }

    @Override
    public boolean matches(Object item) {
        return expected.equals(parseToObject(item));
    }
}