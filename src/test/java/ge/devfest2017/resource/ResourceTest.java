package ge.devfest2017.resource;

import ge.devfest2017.App;
import ge.devfest2017.AppConfig;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.junit.DropwizardAppRule;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import org.junit.BeforeClass;
import org.junit.ClassRule;

/**
 *
 * @author elle
 */
public abstract class ResourceTest {
    private static String baseUrl;
    private static Client client;
    
    @ClassRule
    public static final DropwizardAppRule<AppConfig> APP_RULE =
            new DropwizardAppRule<>(App.class, "config.yml");
    
    @BeforeClass
    public static void init(){
        baseUrl = String.format("http://localhost:%d/api/", APP_RULE.getLocalPort());
        client = new JerseyClientBuilder(APP_RULE.getEnvironment()).build("test client");
    }
    
    protected WebTarget target(String path){
        return client.target(baseUrl + path);
    }
}
