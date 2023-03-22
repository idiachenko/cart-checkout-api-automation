package sephora.cartcheckout.graphql.dto.getshoppinglist;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Variables {
    public Input input;
    public Pagination pagination;
}
