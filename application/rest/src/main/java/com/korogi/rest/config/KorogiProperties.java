package com.korogi.rest.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Daan Peelman
 */
@ConfigurationProperties(prefix = "korogi")

@Setter
@Getter
public class KorogiProperties {
    private OAuth oAuth = new OAuth();
    private Encryption encryption = new Encryption();

    @Setter
    @Getter
    public static class OAuth {
        private String tokenSecret;
        private long tokenExpirationMSec;
        private String frontEndReturnUrl;
    }

    @Setter
    @Getter
    public static class Encryption {
        private String key;
    }
}
