package sephora.graphql;

import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sephora.cartcheckout.assertions.ResponseExpectMessages;
import sephora.cartcheckout.graphql.controller.getshoppinglist.GetShoppingListController;
import sephora.cartcheckout.graphql.dto.getshoppinglist.GetShoppingListDTO;
import sephora.cartcheckout.graphql.dto.getshoppinglist.Input;
import sephora.cartcheckout.graphql.dto.getshoppinglist.Pagination;
import sephora.cartcheckout.graphql.dto.getshoppinglist.Variables;

import java.io.IOException;

import static sephora.cartcheckout.graphql.enums.ShoppingListSortType.RECENTLY;

@Tag("graphql")
@Feature("Shopping List")
@DisplayName("Get Shopping list")
public class GetShoppingListGraphQlTest extends BaseGraphQlTest {

    static GetShoppingListController getShoppingListController = new GetShoppingListController();

    @Test
    @DisplayName("Verify GetShoppingList for with valid fields")
    public void verifyGetShoppingListWithValidFields() throws IOException {
        String preparedKey = "100204";
        Allure.parameter("Profile ID", preparedKey);
        GetShoppingListDTO shoppingListDTO = getShoppingListDTO(
                getShoppingListVariablesDTO(preparedKey,
                        RECENTLY,
                        "any",
                        1,
                        10));

        getShoppingListController.getShoppingList(shoppingListDTO)
                .statusCodeIsEqualTo(ResponseExpectMessages.StatusCode.OK)
                .keyValueEqualsTo(preparedKey);
    }

    @Test
    @DisplayName("Verify GetShoppingList error message when invalid product key is set")
    public void verifyGetShoppingListWithInvalidProductKey() throws IOException {
        String preparedKey = "incorrect";
        GetShoppingListDTO shoppingListDTO = getShoppingListDTO(
                getShoppingListVariablesDTO(preparedKey,
                        RECENTLY,
                        "any",
                        1,
                        10));

        getShoppingListController.getShoppingList(shoppingListDTO)
                .statusCodeIsEqualTo(ResponseExpectMessages.StatusCode.OK)
                .errorMessageContains("Shopping list with id: [" + preparedKey + "-shopping-list] was not found.");
    }

    @Test
    @DisplayName("Verify GetShoppingList with empty values in optional fields")
    public void verifyGetShoppingListWithEmptyValuesInOptionalFields() throws IOException {
        String preparedKey = "100204";
        Allure.parameter("Profile ID", preparedKey);

        Variables variables = Variables.builder()
                .input(Input.builder().key(preparedKey).build())
                .pagination(Pagination.builder().build())
                .build();
        GetShoppingListDTO shoppingListDTO = getShoppingListDTO(variables);

        getShoppingListController.getShoppingList(shoppingListDTO)
                .statusCodeIsEqualTo(ResponseExpectMessages.StatusCode.OK)
                .keyValueEqualsTo(preparedKey);
    }

    @Test
    @DisplayName("Verify GetShoppingList without pagination section which is optional")
    public void verifyGetShoppingListWithoutPaginationSection() throws IOException {
        String preparedKey = "100204";
        Variables variables = Variables.builder()
                .input(Input.builder().key(preparedKey).build())
                .build();
        GetShoppingListDTO shoppingListDTO = getShoppingListDTO(variables);

        getShoppingListController.getShoppingList(shoppingListDTO)
                .statusCodeIsEqualTo(ResponseExpectMessages.StatusCode.OK)
                .keyValueEqualsTo(preparedKey);
    }
}




