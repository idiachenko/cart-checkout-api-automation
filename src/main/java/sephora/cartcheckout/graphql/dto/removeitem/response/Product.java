package sephora.cartcheckout.graphql.dto.removeitem.response;

import lombok.Data;

@Data
public class Product{
	private Object name;
	private String id;
	private Object category;
	private Object type;
	private Sku sku;
}