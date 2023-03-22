package sephora.cartcheckout.graphql.dto.getshoppinglist;


import lombok.Data;

@Data
public class GetShoppingListDTO {

    String query;
    String operationName;
    Variables variables;

    public GetShoppingListDTO() {
    }

}
