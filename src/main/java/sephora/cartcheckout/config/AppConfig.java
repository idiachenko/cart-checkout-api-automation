package sephora.cartcheckout.config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.*;

@LoadPolicy(LoadType.MERGE)
@Sources({"system:properties", "classpath:config.properties"})
public interface AppConfig extends Config {

    @Key("base_graphql_uri")
    @DefaultValue("http://localhost:8080")
    String getGraphqlUri();

    @Key("base_rest_uri")
    @DefaultValue("http://localhost:8081")
    String getRestlUri();

    @Key("base_product_tool_uri")
    @DefaultValue("https://api.us-central1.gcp.commercetools.com")
    String getProductToolUri();

    @Key("ctp_access_token")
    @DefaultValue("Bearer x7ZSVCOiBUBgxDZ2_SJ33ZEbY-T2CYM8")
    String getCtpAccessToken();

}
