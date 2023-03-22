package sephora.cartcheckout.graphql.dto.additem.response;

import java.util.List;
import lombok.Data;

@Data
public class AddItem{
	private List<LineItemsItem> lineItems;
	private Object notification;
	private CustomerProfile customerProfile;
	private String id;
	private String version;
	private String key;
}