package sephora.cartcheckout.product.dto.createproduct.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Dimensions{

	@JsonProperty("w")
	private int w;

	@JsonProperty("h")
	private int h;


}