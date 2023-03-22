package sephora.cartcheckout.graphql.dto.getshoppinglist.response;

import lombok.Data;

@Data
public class Product{
	private Object name;
	private Object attributes;
	private String id;
	private Object productDetails;
	private Object category;
	private Object type;
	private Sku sku;
	private Object brand;
}