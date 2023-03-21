package sephora.graphql;

import sephora.cartcheckout.graphql.dto.getshoppinglist.GetShoppingListDTO;
import sephora.cartcheckout.graphql.dto.getshoppinglist.Input;
import sephora.cartcheckout.graphql.dto.getshoppinglist.Pagination;
import sephora.cartcheckout.graphql.dto.getshoppinglist.Variables;
import sephora.cartcheckout.graphql.enums.ShoppingListSortType;
import sephora.cartcheckout.utils.JsonUtils;

import java.io.IOException;

public class BaseGraphQlTest {

    public Variables getShoppingListVariablesDTO(String key, ShoppingListSortType sortBy, String storeId, Integer currentPage, Integer itemsPerPage){
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
        //todo: get rid of this  link to json (wrap in enum or so)
        String path = "src/test/resources/graphql/query/getShoppingList.json";
        GetShoppingListDTO getShoppingListDTO = JsonUtils.getJsonAsObject(new GetShoppingListDTO(), path);
        getShoppingListDTO.setVariables(variables);

        return getShoppingListDTO;

    }
    

}
