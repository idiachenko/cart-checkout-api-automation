package sephora.cartcheckout.product.dto.createproduct.response;

import lombok.Data;

@Data
public class ImagesItem{
	private String label;
	private String url;
	private Dimensions dimensions;
}