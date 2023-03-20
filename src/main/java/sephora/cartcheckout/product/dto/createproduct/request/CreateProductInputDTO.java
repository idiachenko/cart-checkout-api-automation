package sephora.cartcheckout.product.dto.createproduct.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class CreateProductInputDTO {

	@JsonProperty("name")
	private Name name;

	@JsonProperty("categories")
	private List<Object> categories;

	@JsonProperty("masterVariant")
	private MasterVariant masterVariant;

	@JsonProperty("variants")
	private List<VariantsItem> variants;

	@JsonProperty("productType")
	private ProductType productType;

	@JsonProperty("slug")
	private Slug slug;



}