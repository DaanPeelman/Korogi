package com.korogi.rest.mapper;

import org.springframework.hateoas.Resource;

public interface ResourceMapper<TO, FROM> {
    Resource<TO> toDTOResource(FROM from);
    Class<FROM> fromObjectClass();
}