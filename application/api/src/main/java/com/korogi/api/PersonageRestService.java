package com.korogi.api;

import com.korogi.dto.PersonageDTO;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;

public interface PersonageRestService {
    PagedResources<Resource<PersonageDTO>> getPersonages();
    Resource<PersonageDTO> getPersonageDetails(Long id);
}