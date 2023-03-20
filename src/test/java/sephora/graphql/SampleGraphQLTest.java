package sephora.graphql;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import sephora.cartcheckout.assertions.ResponseExpectMessages;
import sephora.cartcheckout.graphql.controller.getshoppinglist.GetShoppingListController;
import sephora.cartcheckout.graphql.dto.getshoppinglist.GetShoppingListDTO;
import sephora.cartcheckout.graphql.enums.ShoppingListSortType;

import java.io.IOException;
import java.util.stream.Stream;

import static sephora.cartcheckout.graphql.enums.ShoppingListSortType.*;


public class SampleGraphQLTest extends BaseGraphQlTest {

    // todo decide with "shopping list key" - add manually and use in test or generate new products and put them
    //      dynamically in tests, then we need to remove them
    // todo hide paths to JSON files into parameters
    // todo Decide with assertions - they can be common since json response json should be the same for both graphQl and rest
    //todo add before all, before, after all, after methods
    // wrap ENV variables in properties.

    static GetShoppingListController getShoppingListController = new GetShoppingListController();

    @ParameterizedTest
    @MethodSource("getShoppingListPositiveCase1")
    public void healthCheckOfGetShoppingListParameters(String expectedKey,
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

        getShoppingListController.getShoppingList(shoppingListDTO)
                .statusCodeIsEqualTo(ResponseExpectMessages.StatusCode.OK)
                .keyValueEqualsTo(expectedKey);

    }

    /**
     * Verify get shopping list with invalid token, shopping list from the commerce tool profile is returned
     * Verify get shopping list without token (e.g. anonymous request), shopping list from the commerce tool profile is returned
     * Verify get shopping list for SKU that are out of stock, such SKU is not returned
     * Verify get shopping list pagination
     * Verify get shopping list sorting options: default
     * Verify get shopping list sorting options: recently
     * Verify get shopping list sorting options: brandname_asc
     * Verify get shopping list sorting options: brandname_dsc
     * Verify get shopping list sorting options: price_low_to_high
     * Verify get shopping list sorting options: price_high_to_low
     * Verify get shopping list sorting options: store_availability
     */

    private static Stream<Arguments> getShoppingListPositiveCase1() {

        return Stream.of(
                Arguments.of("100201", DEFAULT, "", 1, 10),
                Arguments.of("100204", STOREAVAILABILITY, "not null", 1, -1),
                Arguments.of("100204", BRANDNAME_ASC, "123", 1, 1),
                Arguments.of("100204", BRANDNAME_DESC, "123", 2, 2),
                Arguments.of("100204", PRICE_HIGH_TO_LOW, "123", -1, 100),
                Arguments.of("100204", PRICE_LOW_TO_HIGH, "123", 1, 100),
                Arguments.of("100204", STOREAVAILABILITY, "123", 1, 100)
        );
    }


}
