package sephora.cartcheckout.assertions;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import sephora.cartcheckout.graphql.dto.getshoppinglist.response.GetShoppingListResponse;
import sephora.cartcheckout.graphql.dto.getshoppinglist.response.LineItemsItem;
import sephora.cartcheckout.graphql.dto.removeitem.response.Product;
import sephora.cartcheckout.graphql.dto.removeitem.response.RemoveItemMutationResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class ResponseAssertion {

    private final Response targetResponse;

    public ResponseAssertion(Response targetResponse) {
        this.targetResponse = targetResponse;
    }


    public ResponseAssertion statusCodeIsEqualTo(ResponseExpectMessages.StatusCode expectedStatusCode) {
        var statusCodeAssertionMessage = new ResponseExpectMessages(targetResponse)
                .expectedStatusCode(expectedStatusCode);

        Assertions.assertThat(targetResponse.statusCode())
                .withFailMessage(statusCodeAssertionMessage)
                .isEqualTo(expectedStatusCode.code);
        return this;
    }

    public void responseIsEmpty() {
        Assertions.assertThat(targetResponse.asString()).isEmpty();
    }

    public void responseIsEmptyArray() {
        Assertions.assertThat(targetResponse.asString()).isEqualTo("[]");
    }

    public Response getTargetResponse() {
        return targetResponse;
    }


    public void keyValueEqualsTo(String expected) {
        String actual = targetResponse.jsonPath().get("data.shoppingList.key");
        String suffixThatAddedToKeyInResponse = "-shopping-list";

        var expectedShoppingListKey = new ResponseExpectMessages(targetResponse)
                .expectedShoppingListKey(expected);

        Assertions.assertThat(actual)
                .withFailMessage(expectedShoppingListKey)
                .isEqualTo(expected + suffixThatAddedToKeyInResponse);
    }

    public void errorMessageContains(String errorMessage) {
        List<String> errors = targetResponse.jsonPath().get("errors.message");

        var expectedErrorMessage = new ResponseExpectMessages(targetResponse)
                .expectedErrorMessage(errorMessage);

        Assertions.assertThat(errors)
                .withFailMessage(expectedErrorMessage)
                .contains(errorMessage);
    }

    public ResponseAssertion validateErrorMessage(String expected) {
        Allure.step("Validates the error message is appeared", () -> {
            String actualMessage = targetResponse.jsonPath().get("errors[0].message");
            Assertions.assertThat(actualMessage).isEqualTo(expected);
        });
        return this;
    }

    public void valuesReturnedAsRecentlySortingType() {
        GetShoppingListResponse getShoppingListResponse = targetResponse.getBody().as(GetShoppingListResponse.class);
        List<LineItemsItem> lineItems = getShoppingListResponse.getData().getShoppingList().getLineItems();
        List<LocalDateTime> dates = new ArrayList<>();
        lineItems.forEach(item -> dates.add(LocalDateTime.parse(item.getAddedAt())));
        String expectedTheNewestItem = dates.stream().max(LocalDateTime::compareTo)
                .stream().findFirst().orElseThrow().toString()
                .substring(0, 22); //sometimes zero at the end is cut out in response, that's why put limit

        String expectedTheOldestItem = dates.stream().min(LocalDateTime::compareTo)
                .stream().findFirst().orElseThrow().toString().substring(0, 22);

        String actualTheNewestItem = lineItems.get(0).getAddedAt().substring(0, 22);
        String actualTheOldestItem = lineItems.get(lineItems.size() - 1).getAddedAt().substring(0, 22);

        org.junit.jupiter.api.Assertions.assertEquals(expectedTheNewestItem, actualTheNewestItem);
        org.junit.jupiter.api.Assertions.assertEquals(expectedTheOldestItem, actualTheOldestItem);

    }

    public ResponseAssertion verifySkuRemovedFromShoppingList(String skuId) {
        Allure.step(String.format("Validate the sku: '%s' has been removed", skuId), () -> {
            var skuList = this.targetResponse
                    .then()
                    .extract()
                    .as(RemoveItemMutationResponse.class)
                    .getData()
                    .getRemoveItem()
                    .getLineItems()
                    .stream()
                    .map(sephora.cartcheckout.graphql.dto.removeitem.response.LineItemsItem::getProduct)
                    .map(Product::getSku)
                    .collect(toList());
            assertFalse(skuList.stream().anyMatch(sku -> sku.getId().equals(skuId)));
        });
        return this;
    }
}
