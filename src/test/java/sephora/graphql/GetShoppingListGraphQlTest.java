package sephora.graphql;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import sephora.cartcheckout.assertions.ResponseExpectMessages;
import sephora.cartcheckout.graphql.controller.getshoppinglist.GetShoppingListController;
import sephora.cartcheckout.graphql.dto.getshoppinglist.GetShoppingListDTO;
import sephora.cartcheckout.graphql.dto.getshoppinglist.Input;
import sephora.cartcheckout.graphql.dto.getshoppinglist.Pagination;
import sephora.cartcheckout.graphql.dto.getshoppinglist.Variables;
import sephora.cartcheckout.graphql.enums.ShoppingListSortType;

import java.io.IOException;
import java.util.stream.Stream;

import static sephora.cartcheckout.graphql.enums.ShoppingListSortType.*;


public class GetShoppingListGraphQlTest extends BaseGraphQlTest {

    // todo decide with "shopping list key" - add manually and use in test or generate new products and put them
    //      dynamically in tests, then we need to remove them
    // todo hide paths to JSON files into parameters
    //todo add before all, before, after all, after methods

    static GetShoppingListController getShoppingListController;
    @BeforeAll
    public static void beforeEach(){
        getShoppingListController = new GetShoppingListController();
    }

    @Test
    @DisplayName("Verify GetShoppingList for with valid fields")
    public void verifyGetShoppingListWithValidFields() throws IOException {

        String preparedKey = "100204";
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
    public void verifyGetShoppingListWithoutCustomIdHeader() throws IOException {
        String preparedKey = "incorrect";
        GetShoppingListDTO shoppingListDTO = getShoppingListDTO(
                getShoppingListVariablesDTO(preparedKey,
                        RECENTLY,
                        "any",
                        1,
                        10));

        getShoppingListController.getShoppingList(shoppingListDTO)
                .statusCodeIsEqualTo(ResponseExpectMessages.StatusCode.OK)
                .errorMessageContains("Shopping list with id: ["+ preparedKey + "-shopping-list] was not found.");
    }

    @Test
    @DisplayName("Verify GetShoppingList with empty values in optional fields")
    public void verifyGetShoppingListWithEmptyValuesInOptionalFields() throws IOException {
        String preparedKey = "100204";
        Variables variables = Variables.builder()
                .input(Input.builder().key(preparedKey).build())
                .pagination(Pagination.builder().build())
                .build();
        GetShoppingListDTO shoppingListDTO =  getShoppingListDTO(variables);

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
        GetShoppingListDTO shoppingListDTO =  getShoppingListDTO(variables);

        getShoppingListController.getShoppingList(shoppingListDTO)
                .statusCodeIsEqualTo(ResponseExpectMessages.StatusCode.OK)
                .keyValueEqualsTo(preparedKey);
    }

    @ParameterizedTest
    @MethodSource("verifyFieldsCase")
    public void verifyOptionalFields(String expectedKey,
                             ShoppingListSortType sortType,
                             String storeId,
                             int currentPage,
                             int itemsPerPage) throws IOException {

        GetShoppingListDTO shoppingListDTO = getShoppingListDTO(
                getShoppingListVariablesDTO(expectedKey,
                        sortType,
                        storeId,
                        currentPage,
                        itemsPerPage));

        getShoppingListController
                .getShoppingList(shoppingListDTO)
                .statusCodeIsEqualTo(ResponseExpectMessages.StatusCode.OK)
                .keyValueEqualsTo(expectedKey);

    }

    private static Stream<Arguments> verifyFieldsCase() {

        return Stream.of(
                Arguments.of("100201", INCORRECT, "", 1, 10),
                Arguments.of("100204", STOREAVAILABILITY, "not null", 1, -1),
                Arguments.of("100204", BRANDNAME_ASC, "123", 1, 1),
                Arguments.of("100204", BRANDNAME_DESC, "123", 2, 2),
                Arguments.of("100204", PRICE_HIGH_TO_LOW, "123", -1, 100),
                Arguments.of("100204", PRICE_LOW_TO_HIGH, "123", 1, 100),
                Arguments.of("100204", STOREAVAILABILITY, "123", 1, 100)
        );
    }


}
