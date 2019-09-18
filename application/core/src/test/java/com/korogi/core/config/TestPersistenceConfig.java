package com.korogi.core.config;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import com.korogi.core.datasource.EmbeddedPostgreSQLDataSourceFactory;
import com.korogi.core.domain.BaseEntity;
import com.korogi.core.interceptor.HibernateStatisticsInterceptor;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackageClasses = {
        EmbeddedPostgreSQLDataSourceFactory.class,
        HibernateStatisticsInterceptor.class
})
@EnableTransactionManagement
public class TestPersistenceConfig {

    @Bean
    @Autowired
    public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection(DataSource dataSource) {
        return new DatabaseDataSourceConnectionFactoryBean(dataSource);
    }

    @Bean
    @DependsOn(value = "flyway")
    @Autowired
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, HibernateStatisticsInterceptor hibernateStatisticsInterceptor) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan(BaseEntity.class.getPackage().getName());
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactory.setJpaPropertyMap(jpaProperties(hibernateStatisticsInterceptor));

        return entityManagerFactory;
    }

    private JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setDatabasePlatform(PostgreSQL9Dialect.class.getName());

        return jpaVendorAdapter;
    }

    private Map<String, Object> jpaProperties(HibernateStatisticsInterceptor hibernateStatisticsInterceptor) {
        Map<String, Object> jpaProperties = new HashMap<>();

        jpaProperties.put("hibernate.format_sql", "true");
        jpaProperties.put("hibernate.session_factory.interceptor", hibernateStatisticsInterceptor);

        return jpaProperties;
    }
}