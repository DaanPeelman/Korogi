package com.korogi.rest.mapper;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import com.korogi.core.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

@Component
public class EntityToDTOResourceMapper {
    private final Map<? extends Class<? extends BaseEntity>, ResourceMapper> mappers;

    @Autowired
    public EntityToDTOResourceMapper(
            List<ResourceMapper<?, ? extends BaseEntity>> resourceMappers
    ) {
        mappers = resourceMappers.stream()
                .collect(toMap(
                        ResourceMapper::fromObjectClass,
                        Function.identity()
                ));
    }

    @SuppressWarnings("unchecked")
    public <DTO> Resource<DTO> toDTOResource(BaseEntity entity) {
        return (Resource<DTO>) Optional.ofNullable(mappers.get(entity.getClass()))
                .orElseThrow(() -> new RuntimeException("No mapper found for class '" + entity.getClass() + "'."))
                .toDTOResource(entity);
    }

    public <DTO> PagedResources<Resource<DTO>> toPagedResources(List<? extends BaseEntity> entities, long pageNumber, long totalElements) {
        List<Resource<DTO>> dtos = this.toDTOResourceList(entities);

        PageMetadata metadata = new PageMetadata(dtos.size(), pageNumber, totalElements);
        return new PagedResources<>(dtos, metadata);
    }

    private <DTO> List<Resource<DTO>> toDTOResourceList(List<? extends BaseEntity> entities) {
        return entities.stream()
                .map(this::<DTO>toDTOResource)
                .collect(toList());
    }
}