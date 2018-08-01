package com.korogi.rest.mapper.personage;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.korogi.core.domain.Personage;
import com.korogi.dto.PersonageDTO;
import com.korogi.rest.mapper.ResourceMapper;
import com.korogi.rest.service.PersonageRestServiceImpl;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

@Component
public class PersonageResourceMapper implements ResourceMapper<PersonageDTO, Personage> {
    @Override
    public Resource<PersonageDTO> toDTOResource(Personage personage) {
        Link selfLink = linkTo(methodOn(PersonageRestServiceImpl.class).getPersonageDetails(personage.getId())).withSelfRel();

        return new Resource<>(personage.toDTO(), selfLink);
    }

    @Override
    public Class<Personage> fromObjectClass() {
        return Personage.class;
    }
}