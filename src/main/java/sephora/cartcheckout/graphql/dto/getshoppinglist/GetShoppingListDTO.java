package sephora.cartcheckout.graphql.dto.getshoppinglist;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetShoppingListDTO {

    String query;
    String operationName;
    Variables variables;

    public GetShoppingListDTO() {
    }

}
