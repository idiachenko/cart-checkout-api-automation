package sephora.cartcheckout.graphql.controller;

import io.restassured.specification.RequestSpecification;
import sephora.cartcheckout.BaseRestAssuredClient;

public class BaseGraphQlController extends BaseRestAssuredClient {

    public RequestSpecification graphQlClient(String customerId){
        return client(
                "http://localhost:8080",
                "/shopping-list/graphql?")
                .header("Authorization", "Bearer x7ZSVCOiBUBgxDZ2_SJ33ZEbY-T2CYM8")
                .header("Customer-Id", customerId);
    }


}
