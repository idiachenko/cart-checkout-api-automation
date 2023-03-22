package sephora.cartcheckout.graphql.dto.getshoppinglist.response;

import lombok.Data;

@Data
public class LineItemsItem{
	private String addedAt;
	private Product product;
	private String id;
}