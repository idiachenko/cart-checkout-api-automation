package sephora.cartcheckout.graphql.dto.getshoppinglist.response;

import lombok.Data;

@Data
public class Sku{
	private Object image;
	private Object size;
	private SalePrice salePrice;
	private Price price;
	private String id;
}