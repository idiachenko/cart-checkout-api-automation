package sephora.cartcheckout.graphql.dto.getshoppinglist;

import lombok.Builder;

@Builder
public class Variables {
    public Input input;
    public Pagination pagination;
}
