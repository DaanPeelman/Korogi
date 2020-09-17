package com.korogi.core.config;

import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphJpaRepositoryFactoryBean;
import com.korogi.core.persistence.EntityRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = EntityRepository.class, repositoryFactoryBeanClass = EntityGraphJpaRepositoryFactoryBean.class)
public class RepositoryConfig {
}
