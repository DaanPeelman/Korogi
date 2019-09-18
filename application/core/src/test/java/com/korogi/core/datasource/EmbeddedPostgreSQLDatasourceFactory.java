package com.korogi.core.datasource;

import java.io.IOException;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.springframework.stereotype.Component;

@Component
public class EmbeddedPostgreSQLDatasourceFactory {
    private EmbeddedPostgres embeddedPostgres;

    public DataSource getDatasource() throws IOException {
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