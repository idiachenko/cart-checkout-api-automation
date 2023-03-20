package sephora.cartcheckout.product.dto.createproduct.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImagesItem{

	@JsonProperty("label")
	private String label;

	@JsonProperty("url")
	private String url;

	@JsonProperty("dimensions")
	private Dimensions dimensions;


}