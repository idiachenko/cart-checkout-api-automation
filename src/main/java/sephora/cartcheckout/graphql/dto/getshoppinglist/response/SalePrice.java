package sephora.cartcheckout.graphql.dto.getshoppinglist.response;

import lombok.Data;

@Data
public class SalePrice{
	private Object amount;
	private String currency;
}