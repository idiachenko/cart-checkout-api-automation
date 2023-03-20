package sephora.cartcheckout.product.controller;

import io.restassured.specification.RequestSpecification;
import sephora.cartcheckout.BaseRestAssuredClient;
import sephora.cartcheckout.config.ApplicationConfig;

public class BaseProductClientController extends BaseRestAssuredClient {

    public RequestSpecification productServiceClient() {
        return client(
                ApplicationConfig.config.getProductToolUri(),
                "sephora-qa1/products");

    }
}
