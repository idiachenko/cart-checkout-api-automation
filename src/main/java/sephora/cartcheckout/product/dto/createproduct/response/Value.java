package sephora.cartcheckout.product.dto.createproduct.response;

import lombok.Data;

@Data
public class Value{
	private int centAmount;
	private int fractionDigits;
	private String type;
	private String currencyCode;
}