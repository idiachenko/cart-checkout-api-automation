package sephora.cartcheckout.config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.LoadPolicy;
import static org.aeonbits.owner.Config.LoadType;
import static org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.MERGE)
@Sources({"system:properties", "classpath:config.properties"})
public interface AppConfig extends Config {

    @Key("base_graphql_uri")
    @DefaultValue("http://localhost:8080")
    String getGraphqlUri();

    @Key("base_rest_uri")
    @DefaultValue("http://localhost:8081")
    String getRestlUri();
}
