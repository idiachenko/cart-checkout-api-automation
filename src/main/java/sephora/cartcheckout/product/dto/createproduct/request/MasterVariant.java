package sephora.cartcheckout.product.dto.createproduct.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class MasterVariant{

	@JsonProperty("images")
	private List<ImagesItem> images;

	@JsonProperty("sku")
	private String sku;

	@JsonProperty("prices")
	private List<PricesItem> prices;

}