package sephora.cartcheckout.graphql.dto.getshoppinglist;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Input {
    public String key;
    public Object storeId;
}
