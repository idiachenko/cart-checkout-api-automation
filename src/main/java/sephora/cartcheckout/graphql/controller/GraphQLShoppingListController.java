package sephora.cartcheckout.graphql.controller;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import sephora.cartcheckout.assertions.ResponseAssertion;
import sephora.cartcheckout.graphql.dto.additem.AddItemMutationRequest;
import sephora.cartcheckout.graphql.dto.removeitem.RemoveItemMutationRequest;

public class GraphQLShoppingListController extends BaseGraphQlController {

    private RequestSpecification shoppingListClient() {
        return graphQlClient("denis");
    }

    @Step("Send remove item from the shopping list query")
    public ResponseAssertion removeItemFromShoppingList(RemoveItemMutationRequest request) {
        final String skuId = request.getVariables().removeItemInput.skuId;
        return Allure.step(String.format("Removes sku: '%s' from the shopping list", skuId), () -> {
            var unpublishedDealResponse = shoppingListClient()
                    .body(request)
                    .when()
                    .post();
            return new ResponseAssertion(unpublishedDealResponse);
        });
    }

    @Step("Send remove item from the shopping list query")
    public ResponseAssertion addItemToShoppingList(AddItemMutationRequest request) {
        return Allure.step("Add sku to shopping list", () -> {
            var unpublishedDealResponse = shoppingListClient()
                    .body(request)
                    .log().all()
                    .when()
                    .post();
            return new ResponseAssertion(unpublishedDealResponse);
        });
    }
}
