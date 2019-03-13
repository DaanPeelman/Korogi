package com.korogi.webapp.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * @author Daan Peelman
 */
class CacheableResourcePatternUtilTest {
    private CacheableResourcePatternUtil util;

    @BeforeEach
    void setup() {
        util = new CacheableResourcePatternUtil();
    }

    @Test
    void isCacheableResource_css() throws Exception {
        assertThat(util.isCacheableResource("test.css")).isTrue();
        assertThat(util.isCacheableResource("/test.css")).isTrue();
        assertThat(util.isCacheableResource("/assets/css/test.css")).isTrue();
        assertThat(util.isCacheableResource("/styles.9b5bb36c6279021f799f.bundle.css")).isTrue();
    }

    @Test
    void isCacheableResource_js() throws Exception {
        assertThat(util.isCacheableResource("test.js")).isTrue();
        assertThat(util.isCacheableResource("/test.js")).isTrue();
        assertThat(util.isCacheableResource("/assets/js/test.js")).isTrue();
        assertThat(util.isCacheableResource("/inline.318b50c57b4eba3d437b.bundle.js")).isTrue();
        assertThat(util.isCacheableResource("/polyfills.678fcb9459b6e0919581.bundle.js")).isTrue();
        assertThat(util.isCacheableResource("/main.e47a192ded66112453f7.bundle.js")).isTrue();
    }

    @Test
    void isCacheableResource_jpg() throws Exception {
        assertThat(util.isCacheableResource("test.jpg")).isFalse();
        assertThat(util.isCacheableResource("/test.jpg")).isFalse();
        assertThat(util.isCacheableResource("/assets/images/test.jpg")).isFalse();
    }

    @Test
    void isCacheableResource_png() throws Exception {
        assertThat(util.isCacheableResource("test.png")).isFalse();
        assertThat(util.isCacheableResource("/test.png")).isFalse();
        assertThat(util.isCacheableResource("/assets/images/test.png")).isFalse();
    }

    @Test
    void isCacheableResource_gif() throws Exception {
        assertThat(util.isCacheableResource("test.gif")).isFalse();
        assertThat(util.isCacheableResource("/test.gif")).isFalse();
        assertThat(util.isCacheableResource("/assets/images/test.gif")).isFalse();
    }

    @Test
    void isCacheableResource_noExtension() throws Exception {
        assertThat(util.isCacheableResource("")).isFalse();
        assertThat(util.isCacheableResource("/")).isFalse();
        assertThat(util.isCacheableResource("/anime/1")).isFalse();
    }

    @Test
    void isCacheableResource_html() throws Exception {
        assertThat(util.isCacheableResource("index.html")).isFalse();
        assertThat(util.isCacheableResource("/index.html")).isFalse();
        assertThat(util.isCacheableResource("/anime/index.html")).isFalse();
    }
}
