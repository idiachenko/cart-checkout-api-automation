package sephora.cartcheckout.graphql.controller;

import io.restassured.specification.RequestSpecification;
import sephora.cartcheckout.BaseRestAssuredClient;
import sephora.cartcheckout.config.ApplicationConfig;

public class BaseGraphQlController extends BaseRestAssuredClient {

    public RequestSpecification graphQlClient(String customerId) {
        return client(
                ApplicationConfig.config.getGraphqlUri(),
                "/shopping-list/graphql?")
                .header("Authorization", "Bearer x7ZSVCOiBUBgxDZ2_SJ33ZEbY-T2CYM8")
                .header("Customer-Id", customerId);
    }


}
