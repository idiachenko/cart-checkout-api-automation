package sephora.cartcheckout.product.dto.createproduct.response;

import lombok.Data;

import java.util.List;

@Data
public class Current{
	private CategoryOrderHints categoryOrderHints;
	private SearchKeywords searchKeywords;
	private Name name;
	private List<Object> categories;
	private MasterVariant masterVariant;
	private List<VariantsItem> variants;
	private Slug slug;
}