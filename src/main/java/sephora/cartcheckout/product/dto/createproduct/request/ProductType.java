package sephora.cartcheckout.product.dto.createproduct.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductType{

	@JsonProperty("typeId")
	private String typeId;

	@JsonProperty("id")
	private String id;

}