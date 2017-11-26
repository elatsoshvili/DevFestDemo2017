package ge.devfest2017;

import ge.devfest2017.dao.ReviewDAO;
import ge.devfest2017.resource.ReviewResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayBundle;
import io.dropwizard.flyway.FlywayFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.util.List;
import org.skife.jdbi.v2.DBI;

/**
 *
 * @author elle
 */
public class App extends Application<AppConfig> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public String getName() {
        return "DevFest 2017 Demo";
    }

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                                                   new EnvironmentVariableSubstitutor(false)
                )
        );
        
        bootstrap.addBundle(new FlywayBundle<AppConfig>() {
            
            @Override
            public DataSourceFactory getDataSourceFactory(AppConfig configuration) {
                return configuration.getDataSourceFactory();
            }
            
            @Override
            public FlywayFactory getFlywayFactory(AppConfig configuration) {
                return configuration.getFlywayFactory();
            }
        });
    }


    @Override
    public void run(AppConfig config,
                    Environment environment) {
       
        final DBIFactory factory = new DBIFactory();
        
        // I\m not keeping database name in url configuration, as I want flyway
        // to create database, when there is none. So, get name from flyway config
        String databaseUrl = config.getDataSourceFactory().getUrl();
        List<String> schemas = config.getFlywayFactory().getSchemas();
        if (schemas.size() > 0) {
            databaseUrl += "/" + schemas.get(0);
        }
        config.getDataSourceFactory().setUrl(databaseUrl);
        
        
        final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "mysql");
        final ReviewDAO dao = jdbi.onDemand(ReviewDAO.class);
        environment.jersey().register(new ReviewResource(dao));
    }
    
}
