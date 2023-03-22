package sephora.cartcheckout.graphql.dto.removeitem.response;

import lombok.Data;

@Data
public class LineItemsItem {
    private Object addedAt;
    private Product product;
    private String id;
}