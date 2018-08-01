package com.korogi.rest.service.matcher;

import com.korogi.dto.AnimeDTO;
import com.korogi.dto.EpisodeDTO;
import com.korogi.dto.ErrorDTO;
import com.korogi.dto.PersonageDTO;
import org.hamcrest.Matcher;
import org.springframework.hateoas.Link;

public class DTOMatchers {
    public static Matcher<ErrorDTO> matchesErrorDTO(ErrorDTO expected) {
        return new DTOMatcher<>(expected, ErrorDTO.class);
    }

    public static Matcher<AnimeDTO> matchesAnimeDTO(AnimeDTO expected) {
        return new DTOMatcher<>(expected, AnimeDTO.class);
    }

    public static Matcher<EpisodeDTO> matchesEpisodeDTO(EpisodeDTO expected) {
        return new DTOMatcher<>(expected, EpisodeDTO.class);
    }

    public static Matcher<EpisodeDTO[]> matchesEpisodeDTOs(EpisodeDTO[] expected) {
        return new DTOsMatcher<>(expected, EpisodeDTO[].class);
    }

    public static Matcher<PersonageDTO> matchesPersonageDTO(PersonageDTO expected) {
        return new DTOMatcher<>(expected, PersonageDTO.class);
    }

    public static Matcher<PersonageDTO[]> matchesPersonageDTOs(PersonageDTO[] expected) {
        return new DTOsMatcher<>(expected, PersonageDTO[].class);
    }

    public static Matcher<Link[]> containsResourceLinks(String... expectedRelations) {
        return new ResourceLinkMatcher(expectedRelations);
    }
}