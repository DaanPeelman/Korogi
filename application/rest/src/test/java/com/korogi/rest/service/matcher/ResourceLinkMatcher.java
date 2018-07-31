package com.korogi.rest.service.matcher;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import org.hamcrest.Description;
import org.springframework.hateoas.Link;

public class ResourceLinkMatcher extends DTOMatchers.BaseDTOMatcher<Link[]> {
    public ResourceLinkMatcher(String... expectedRelations) {
        super(Arrays.stream(expectedRelations).map(rel -> new Link("/", rel)).toArray(Link[]::new), Link[].class);
    }

    @Override
    public boolean matches(Object item) {
        List<String> expectedRelations = toRelationArray(expected);
        List<String> actualRelations = toRelationArray(parseToObject(item));

        return actualRelations.equals(expectedRelations);
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(toRelationArray(expected));
    }

    @Override
    public void describeMismatch(Object item, Description description) {
        description.appendText("was ").appendValue(toRelationArray(parseToObject(item)));
    }

    private List<String> toRelationArray(Link[] links) {
        return Arrays.stream(links).map(Link::getRel).collect(toList());
    }
}