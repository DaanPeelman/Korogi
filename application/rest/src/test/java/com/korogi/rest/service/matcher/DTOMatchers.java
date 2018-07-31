package com.korogi.rest.service.matcher;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.korogi.dto.AnimeDTO;
import com.korogi.dto.ErrorDTO;
import com.korogi.rest.mapper.JavaTimeCompliantJsonMapper;
import lombok.SneakyThrows;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.springframework.hateoas.Link;

public class DTOMatchers {
    public static Matcher<ErrorDTO> matchesErrorDTO(ErrorDTO expected) {
        return new ErrorDTOMatcher(expected);
    }

    public static Matcher<AnimeDTO> matchesAnimeDTO(AnimeDTO expected) {
        return new AnimeDTOMatcher(expected);
    }

    public static Matcher<Link[]> containsResourceLinks(String... expectedRelations) {
        return new ResourceLinkMatcher(expectedRelations);
    }

    static abstract class BaseDTOMatcher<T> extends BaseMatcher<T> {
        protected static final ObjectMapper MAPPER = new JavaTimeCompliantJsonMapper();
        private static final Map<String, Object> MAP = new LinkedHashMap<>();

        protected final T expected;
        private final Class<T> clazz;

        public BaseDTOMatcher(
                T expected,
                Class<T> clazz
        ) {
            this.expected = expected;
            this.clazz = clazz;
        }

        @Override
        public void describeTo(Description description) {
            description.appendValue(parseToExpectedMap(expected));
        }

        @SuppressWarnings("unchecked")
        @SneakyThrows
        private Map<String, Object> parseToExpectedMap(T item) {
            try (StringWriter stringWriter = new StringWriter()) {
                MAPPER.writeValue(stringWriter, item);
                return MAPPER.readValue(stringWriter.toString(), MAP.getClass());
            }
        }

        @SneakyThrows
        protected T parseToObject(Object item) {
            try (StringWriter stringWriter = new StringWriter()) {
                MAPPER.writeValue(stringWriter, item);
                return MAPPER.readValue(stringWriter.toString(), clazz);
            }
        }
    }
}