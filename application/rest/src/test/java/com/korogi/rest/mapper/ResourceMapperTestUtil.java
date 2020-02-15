package com.korogi.rest.mapper;

import static java.util.Collections.emptyList;

import org.springframework.hateoas.Link;

/**
 * @author Daan Peelman
 */
public class ResourceMapperTestUtil {
    public static Link toLinkWithNoAffordances(Link link) {
        return link.withAffordances(emptyList());
    }
}