package sephora.cartcheckout.graphql.dto.getshoppinglist;

import lombok.Builder;
import sephora.cartcheckout.graphql.enums.ShoppingListSortType;

@Builder
public class Pagination {
    public ShoppingListSortType sortBy;
    public int itemsPerPage;
    public int currentPage;
}
