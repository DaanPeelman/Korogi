package com.korogi.api;

import com.korogi.dto.PersonageDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

public interface PersonageRestService {
    PagedModel<EntityModel<PersonageDTO>> getPersonages();
    EntityModel<PersonageDTO> getPersonageDetails(Long id);
}