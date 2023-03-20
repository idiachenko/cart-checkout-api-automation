package sephora.cartcheckout.product.dto.createproduct.response;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class MasterVariant{
	private List<ImagesItem> images;
	private List<Object> assets;
	private List<Object> attributes;
	private int id;
	private String sku;
	private List<PricesItem> prices;
}