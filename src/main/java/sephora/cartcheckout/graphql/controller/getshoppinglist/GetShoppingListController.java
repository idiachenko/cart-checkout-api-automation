package sephora.cartcheckout.graphql.controller.getshoppinglist;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import sephora.cartcheckout.assertions.ResponseAssertion;
import sephora.cartcheckout.graphql.controller.BaseGraphQlController;
import sephora.cartcheckout.graphql.dto.getshoppinglist.GetShoppingListDTO;

public class GetShoppingListController extends BaseGraphQlController {

    private RequestSpecification getShoppingListClient() {
        return graphQlClient("automation");
    }

    @Step("Run get shopping list query")
    public ResponseAssertion getShoppingList(GetShoppingListDTO getShoppingListDTO) {
        var unpublishedDealResponse = getShoppingListClient()
                .body(getShoppingListDTO)
                .post();
        return new ResponseAssertion(unpublishedDealResponse);
    }

    public void setHeader(String key, String value){
        getShoppingListClient().header(key, value);
    }
}
