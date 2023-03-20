package sephora.cartcheckout.product.dto.createproduct.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PricesItem{

	@JsonProperty("value")
	private Value value;

	public Value getValue(){
		return value;
	}
}