package com.korogi.rest.services;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Arrays;
import java.util.List;
import com.korogi.api.AnimeRestService;
import com.korogi.api.dto.AnimeDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/anime")
public class AnimeRestServiceImpl implements AnimeRestService {
    @RequestMapping(method = RequestMethod.GET)
    @Override
    public @ResponseBody List<AnimeDTO> getAnime() {
        AnimeDTO a1 = new AnimeDTO();
        a1.setName("Anime 1");

        a1.add(linkTo(methodOn(AnimeRestServiceImpl.class).getAnime()).withSelfRel());

        AnimeDTO a2 = new AnimeDTO();
        a2.setName("Anime 2");

        a2.add(linkTo(methodOn(AnimeRestServiceImpl.class).getAnime()).withSelfRel());

        return Arrays.asList(a1, a2);
    }
}
