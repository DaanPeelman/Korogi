package com.korogi.core.datasource;

import java.io.IOException;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;

public class EmbeddedPostgreSQLDatasourceFactory {
    private EmbeddedPostgres embeddedPostgres;

    public DataSource getDatasource() throws IOException {
        embeddedPostgres = EmbeddedPostgres.start();

        return embeddedPostgres.getPostgresDatabase();
    }

    @PreDestroy
    public void onDestroy() throws IOException {
        embeddedPostgres.close();
    }
}