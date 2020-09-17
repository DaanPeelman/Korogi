package com.korogi.rest.util;

import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.SneakyThrows;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;

public class MockMvcAssertionUtil {
    private final ResultActions resultActions;

    public MockMvcAssertionUtil(ResultActions resultActions) {
        this.resultActions = resultActions;
    }

    @SneakyThrows
    public MockMvcAssertionUtil andExpectResponseMatchingFile(String filePath) {
        byte[] actualContent = this.andReturn().getResponse().getContentAsByteArray();
        String actualContentAsString = new String(actualContent);

        byte[] expectedContent = Files.readAllBytes(Paths.get(this.getClass().getClassLoader().getResource(filePath).toURI()));
        String expectedContentAsString = new String(expectedContent);

        JSONAssert.assertEquals(expectedContentAsString, actualContentAsString, JSONCompareMode.STRICT);

        return this;
    }

    public MockMvcAssertionUtil andExpect(ResultMatcher matcher) throws Exception {
        resultActions.andExpect(matcher);
        return this;
    }

    public MockMvcAssertionUtil andDo(ResultHandler handler) throws Exception {
        resultActions.andDo(handler);
        return this;
    }

    public MvcResult andReturn() {
        return resultActions.andReturn();
    }
}