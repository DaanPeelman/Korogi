package com.korogi.core.datasource;

import java.io.IOException;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class EmbeddedPostgreSQLDataSourceFactory extends AbstractFactoryBean<DataSource> {
    private EmbeddedPostgres embeddedPostgres;

    @Override
    public Class<DataSource> getObjectType() {
        return DataSource.class;
    }

    @Override
    protected DataSource createInstance() throws Exception {
        if (embeddedPostgres == null) {
            embeddedPostgres = EmbeddedPostgres.start();
        }

        return embeddedPostgres.getPostgresDatabase();
    }

    @PreDestroy
    public void onDestroy() throws IOException {
        embeddedPostgres.close();
    }
}