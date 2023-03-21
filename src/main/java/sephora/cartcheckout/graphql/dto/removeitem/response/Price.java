package sephora.cartcheckout.graphql.dto.removeitem.response;

import lombok.Data;

@Data
public class Price{
	private Object amount;
	private String currency;
}