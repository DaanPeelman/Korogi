package com.korogi.core.config;

import java.util.Properties;
import javax.sql.DataSource;
import com.korogi.core.domain.BaseEntity;
import org.hibernate.c3p0.internal.C3P0ConnectionProvider;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.postgresql.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class PersistenceConfig {

    @Bean
    public DataSource dataSource() {
        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();

        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setUrl("jdbc:postgresql://korogi-postgres:5432/postgres");
        dataSource.setUsername("korogi");
        dataSource.setPassword("korogi");

        return dataSource;
    }

    @Bean
    @DependsOn("flyway")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setPackagesToScan(BaseEntity.class.getPackage().getName());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(jpaProperties());

        return entityManagerFactoryBean;
    }

    private JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

        jpaVendorAdapter.setShowSql(false);
        jpaVendorAdapter.setDatabasePlatform(PostgreSQL9Dialect.class.getName());

        return jpaVendorAdapter;
    }

    private Properties jpaProperties() {
        Properties jpaProperties = new Properties();

        jpaProperties.setProperty("hibernate.connection.driver_class", Driver.class.getName());
        jpaProperties.setProperty("hibernate.connection.url", "jdbc:postgresql://korogi-postgres:5432/postgres");
        jpaProperties.setProperty("hibernate.connection.username", "korogi");
        jpaProperties.setProperty("hibernate.connection.password", "korogi");

        jpaProperties.setProperty("hibernate.connection.provider_class", C3P0ConnectionProvider.class.getName());

        jpaProperties.setProperty("hibernate.c3p0.min_size", "1");
        jpaProperties.setProperty("hibernate.c3p0.max_size", "10");
        jpaProperties.setProperty("hibernate.c3p0.timeout", "600");
        jpaProperties.setProperty("hibernate.c3p0.max_statements", "10");
        jpaProperties.setProperty("hibernate.c3p0.idle_test_period", "3000");
        jpaProperties.setProperty("hibernate.c3p0.acquire_increment", "1");

        return jpaProperties;
    }
}