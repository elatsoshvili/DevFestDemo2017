package ge.devfest2017;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayFactory;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author elle
 */
public class AppConfig extends Configuration {
    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();
    
    @Valid
    @NotNull
    private FlywayFactory flyway = new FlywayFactory();

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
    
    @JsonProperty("flyway")
    public void setFlywayFactory(FlywayFactory factory) {
        this.flyway = factory;
    }

    @JsonProperty("flyway")
    public FlywayFactory getFlywayFactory() {
        return flyway;
    }
    
    
}
