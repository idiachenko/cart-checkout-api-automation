package sephora.cartcheckout.product.dto.createproduct.response;

import lombok.Data;

import java.util.List;

@Data
public class VariantsItem{
	private List<ImagesItem> images;
	private List<Object> assets;
	private List<Object> attributes;
	private int id;
	private List<Object> prices;
}