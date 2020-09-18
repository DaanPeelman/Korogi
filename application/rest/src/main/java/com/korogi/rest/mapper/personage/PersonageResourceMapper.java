package com.korogi.rest.mapper.personage;

import static com.korogi.rest.mapper.BaseResourceMapper.COMPONENT_MODEL;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import com.korogi.core.domain.Personage;
import com.korogi.dto.PersonageDTO;
import com.korogi.rest.mapper.BaseResourceMapper;
import com.korogi.rest.service.PersonageRestServiceImpl;
import org.mapstruct.Mapper;
import org.springframework.hateoas.Link;

@Mapper(componentModel = COMPONENT_MODEL)
abstract class PersonageResourceMapper extends BaseResourceMapper<PersonageDTO, Personage> {

    public PersonageResourceMapper() {
        super(Personage.class);
    }

    @Override
    protected List<Link> createLinks(Personage personage) {
        List<Link> links = new ArrayList<>();

        links.add(linkTo(methodOn(PersonageRestServiceImpl.class).getPersonageDetails(personage.getId())).withSelfRel());

        return links;
    }
}