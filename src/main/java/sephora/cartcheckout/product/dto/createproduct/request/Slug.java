package sephora.cartcheckout.product.dto.createproduct.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Slug{

	@JsonProperty("en")
	private String en;

}