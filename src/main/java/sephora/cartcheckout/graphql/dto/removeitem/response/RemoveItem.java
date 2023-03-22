package sephora.cartcheckout.graphql.dto.removeitem.response;

import lombok.Data;

import java.util.List;

@Data

public class RemoveItem {
    private List<LineItemsItem> lineItems;
    private Owner owner;
    private Audit audit;
    private String shareLink;
    private String id;
    private int version;
    private String key;
    private Customer customer;

	@Data
    public static class Owner {
		private String name;
		private String biStatus;
    }
}