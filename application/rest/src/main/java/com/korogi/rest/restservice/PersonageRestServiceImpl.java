package com.korogi.rest.restservice;

import static lombok.AccessLevel.PUBLIC;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.korogi.api.PersonageRestService;
import com.korogi.core.domain.Personage;
import com.korogi.core.persistence.personage.PersonageRepository;
import com.korogi.dto.PersonageDTO;
import com.korogi.rest.exception.ResourceNotFoundException;
import com.korogi.rest.mapper.EntityToDTOResourceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/personages", produces = { APPLICATION_JSON_VALUE })
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = PUBLIC)
public class PersonageRestServiceImpl implements PersonageRestService {
    private static final String PATH_VARIABLE_ID = "id";

    private final PersonageRepository personageRepository;
    private final EntityToDTOResourceMapper entityToDTOResourceMapper;

    @GetMapping
    @ResponseStatus(OK)
    @Override
    public PagedModel<EntityModel<PersonageDTO>> getPersonages() {
        return null;
    }

    @GetMapping(value = "{" + PATH_VARIABLE_ID + "}")
    @ResponseStatus(OK)
    @Override
    public EntityModel<PersonageDTO> getPersonageDetails(
        @PathVariable(PATH_VARIABLE_ID) Long id
    ) {
        Personage personage = personageRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return entityToDTOResourceMapper.toDTOResource(personage);
    }
}