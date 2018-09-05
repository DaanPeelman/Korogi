package com.korogi.rest.mapper;

import java.util.List;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public abstract class BaseResourceMapper<TO, FROM> implements ResourceMapper<TO, FROM> {
    public static final String COMPONENT_MODEL = "spring";

    private final Class<FROM> toClass;

    public BaseResourceMapper(Class<FROM> toClass) {
        this.toClass = toClass;
    }

    @Override
    public Resource<TO> toDTOResource(FROM from) {
        return new Resource<>(map(from), createLinks(from));
    }

    @Override
    public Class<FROM> fromObjectClass() {
        return toClass;
    }

    protected abstract TO map(FROM from);

    protected abstract List<Link> createLinks(FROM from);
}