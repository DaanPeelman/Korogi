package com.korogi;

import com.korogi.rest.config.KorogiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(KorogiProperties.class)
public class KorogiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KorogiApplication.class, args);
	}

}
