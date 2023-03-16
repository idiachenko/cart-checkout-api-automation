package sephora.cartcheckout.graphql.dto.getshoppinglist;

import lombok.Builder;

@Builder
public class Input {
    public String key;
    public Object storeId;
}
