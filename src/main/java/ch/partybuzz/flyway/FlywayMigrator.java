package ch.partybuzz.flyway;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.flywaydb.core.Flyway;

@ApplicationScoped
public class FlywayMigrator {
    private final Config config = ConfigProvider.getConfig();
    private final boolean runMigration = config.getValue("partybuzz.flyway.run-migration", Boolean.class);
    private final String datasourceUrl = "jdbc:" + config.getValue("quarkus.datasource.reactive.url", String.class);
    private final String datasourceUsername = config.getValue("quarkus.datasource.username", String.class);
    private final String datasourcePassword = ""; //config.getValue("quarkus.datasource.password", String.class);

    public void runFlywayMigration(@Observes StartupEvent event) {
        if (runMigration) {
            Flyway flyway = Flyway.configure()
                    .dataSource(datasourceUrl, datasourceUsername, datasourcePassword)
                    .load();
            flyway.migrate();
        }
    }
}
