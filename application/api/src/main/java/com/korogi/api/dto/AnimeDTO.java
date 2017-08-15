package com.korogi.api.dto;

import org.springframework.hateoas.ResourceSupport;

public class AnimeDTO extends ResourceSupport {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
