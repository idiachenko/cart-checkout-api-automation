package sephora.cartcheckout.graphql.dto.getshoppinglist.response;

import java.util.List;
import lombok.Data;

@Data
public class ShoppingList{
	private List<LineItemsItem> lineItems;
	private Audit audit;
	private String shareLink;
	private String id;
	private int version;
	private String key;
}