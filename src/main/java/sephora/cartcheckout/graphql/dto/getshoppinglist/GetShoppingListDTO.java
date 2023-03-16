package sephora.cartcheckout.graphql.dto.getshoppinglist;


public class GetShoppingListDTO {

    String query;
    String operationName;
    Variables variables;

    public GetShoppingListDTO(){}


    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public Variables getVariables() {
        return variables;
    }

    public void setVariables(Variables variables) {
        this.variables = variables;
    }



}
