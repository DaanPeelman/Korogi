package com.korogi.rest.util;

import static java.nio.charset.StandardCharsets.UTF_8;
import static lombok.AccessLevel.PRIVATE;

import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.test.web.servlet.ResultMatcher;

@NoArgsConstructor(access = PRIVATE)
public class CustomContentResultMatchers {
    public static CustomJsonContentResultMatcher json() {
        return new CustomJsonContentResultMatcher();
    }

    public static class CustomJsonContentResultMatcher {
        @SneakyThrows
        public ResultMatcher matchesFileContent(String filePath) {
            String expectedContent = Files.readString(Paths.get(this.getClass()
                                                                    .getClassLoader()
                                                                    .getResource(filePath)
                                                                    .toURI()), UTF_8);
            return result -> JSONAssert.assertEquals(
                expectedContent,
                result.getResponse().getContentAsString(UTF_8),
                JSONCompareMode.STRICT
            );
        }
    }
}
