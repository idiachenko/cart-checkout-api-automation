package sephora.cartcheckout.graphql.dto.getshoppinglist.response;

import lombok.Data;

@Data
public class Price{
	private Object amount;
	private String currency;
}