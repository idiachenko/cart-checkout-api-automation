package sephora.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import sephora.cartcheckout.graphql.dto.getshoppinglist.GetShoppingListDTO;
import sephora.cartcheckout.graphql.dto.getshoppinglist.Input;
import sephora.cartcheckout.graphql.dto.getshoppinglist.Pagination;
import sephora.cartcheckout.graphql.dto.getshoppinglist.Variables;
import sephora.cartcheckout.graphql.enums.ShoppingListSortType;

import java.io.IOException;
import java.nio.file.Paths;

public class BaseGraphQlTest {

    public Variables getShoppingListVariablesDTO(String key, ShoppingListSortType sortBy, String storeId, int currentPage, int itemsPerPage){
        return Variables.builder()
                .input(Input.builder()
                        .key(key)
                        .storeId(storeId).build())
                .pagination(Pagination.builder()
                        .currentPage(currentPage)
                        .itemsPerPage(itemsPerPage)
                        .sortBy(sortBy)
                        .build()).build();
    }

    public GetShoppingListDTO getShoppingListDTO (Variables variables) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        GetShoppingListDTO getShoppingListDTO = new GetShoppingListDTO(); //Jakson needs class to be initialized
        getShoppingListDTO = mapper.readValue(
                Paths.get("src/test/resources/graphql/query/getShoppingList.json").toFile(),
                GetShoppingListDTO.class);
        getShoppingListDTO.setVariables(variables);

        return getShoppingListDTO;


    }
}
