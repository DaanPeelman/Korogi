package com.korogi.api.hateoas;

import java.util.HashMap;
import java.util.Map;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class EmbeddedResource<T> extends Resource<T> {
    private Map<String, Object> embedded;

    public EmbeddedResource(T content, Link... links) {
        super(content, links);
        embedded = new HashMap<>();
    }

    public EmbeddedResource(T content, Iterable<Link> links) {
        super(content, links);
        embedded = new HashMap<>();
    }

    public void addEmbedded(String key, Object embeddedObject) {
        embedded.put(key, embeddedObject);
    }

    public Map<String, Object> getEmbedded() {
        return embedded;
    }
}