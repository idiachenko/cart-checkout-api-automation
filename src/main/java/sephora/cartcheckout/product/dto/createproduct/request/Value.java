package sephora.cartcheckout.product.dto.createproduct.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Value{

	@JsonProperty("centAmount")
	private int centAmount;

	@JsonProperty("currencyCode")
	private String currencyCode;

}