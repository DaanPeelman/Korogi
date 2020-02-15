package com.korogi.rest.mapper;

import org.springframework.hateoas.EntityModel;

public interface ResourceMapper<TO, FROM> {
    EntityModel<TO> toDTOResource(FROM from);
    Class<FROM> fromObjectClass();
}