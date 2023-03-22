package sephora.cartcheckout.graphql.dto.additem.response;

import lombok.Data;

@Data
public class LineItemsItem{
	private String addedAt;
	private Variant variant;
	private String id;
}