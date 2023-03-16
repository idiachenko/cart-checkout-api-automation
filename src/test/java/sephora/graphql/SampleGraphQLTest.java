package sephora.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import sephora.cartcheckout.assertions.ResponseExpectMessages;
import sephora.cartcheckout.graphql.controller.getshoppinglist.GetShoppingListController;
import sephora.cartcheckout.graphql.dto.getshoppinglist.GetShoppingListDTO;
import sephora.cartcheckout.graphql.dto.getshoppinglist.Input;
import sephora.cartcheckout.graphql.dto.getshoppinglist.Pagination;
import sephora.cartcheckout.graphql.dto.getshoppinglist.Variables;
import sephora.cartcheckout.graphql.enums.ShoppingListSortType;

import java.io.IOException;
import java.nio.file.Paths;

import static sephora.cartcheckout.graphql.enums.ShoppingListSortType.STOREAVAILABILITY;

public class SampleGraphQLTest {

    static GetShoppingListController getShoppingListController = new GetShoppingListController();
    @Test
    public void verifyGetShoppingList() throws IOException {

        // todo put in data provider all additional variables
        // todo put in helper/util class generation of new basic DTO like Variables below
        // todo decide with "shopping list key" - add manually and use in test or generate new products and put them
        //      dynamically in tests, then we need to remove them
        // todo hide paths to JSON files into parameters
        // todo Decide with assertions - they can be common since json response json should be the same for both graphQl and rest
        //todo add before all, before, after all, after methods
        // wrap ENV variables in properties.

        String preparedKey = "100201";

        GetShoppingListDTO shoppingListDTO = getShoppingListDTO(
                getVariables(preparedKey,
                        STOREAVAILABILITY,
                        1,
                        2));

        getShoppingListController.getShoppingList(shoppingListDTO)
                .statusCodeIsEqualTo(ResponseExpectMessages.StatusCode.OK)
                .keyValueEqualsTo(preparedKey);
    }

    private static Variables getVariables(String key, ShoppingListSortType sortBy, int currentPage, int itemsPerPage){
        return Variables.builder()
                .input(Input.builder()
                        .key(key)
                        .storeId("").build())
                .pagination(Pagination.builder()
                        .currentPage(currentPage)
                        .itemsPerPage(itemsPerPage)
                        .sortBy(sortBy)
                        .build()).build();
    }

    private static GetShoppingListDTO getShoppingListDTO (Variables variables) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // convert a JSON string to a Book object
        GetShoppingListDTO getShoppingListDTO = new GetShoppingListDTO(); //Jakson needs class to be initialized todo to fix
        getShoppingListDTO = mapper.readValue(
                Paths.get("src/test/resources/graphql/query/getShoppingList.json").toFile(),
                GetShoppingListDTO.class);
        getShoppingListDTO.setVariables(variables);

        return getShoppingListDTO;


    }

}
