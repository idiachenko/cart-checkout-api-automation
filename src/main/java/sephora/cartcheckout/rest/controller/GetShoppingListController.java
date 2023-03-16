package sephora.cartcheckout.rest.controller;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import sephora.cartcheckout.assertions.ResponseAssertion;

public class GetShoppingListController extends BaseRestController {

    private RequestSpecification shoppingListApi() {
        return restClient("/<base_path_of_the_Controller>/", "User Data object or string if needed");
    }

    @Step("Trigger POST request for shopping list endpoint")
    public ResponseAssertion createNewShoppingList(Object dataTransferObjectOfSHoppingListEndpointWithData) {
        var unpublishedDealResponse = shoppingListApi().body(dataTransferObjectOfSHoppingListEndpointWithData)
                .log().all().post("/path_if_needed");
        return new ResponseAssertion(unpublishedDealResponse);
    }


}
