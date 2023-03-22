package sephora.cartcheckout.graphql.dto.removeitem.response;

import lombok.Data;

@Data
public class Sku{
	private Object image;
	private Object images;
	private Object size;
	private SalePrice salePrice;
	private Price price;
	private Object variant;
	private String id;
}