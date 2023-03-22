package sephora.cartcheckout.graphql.dto.additem.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AddItemMutationResponse {
    @JsonProperty("data")
    private ResponseDataField data;

    @Data
    public static class ResponseDataField {
        private AddItem addItem;

        @Data
        public static class AddItem {
            private List<LineItemsItem> lineItems;
            private Notification notification;
            private CustomerProfile customerProfile;
            private String id;
            private String version;
            private String key;


            @Data
            public static class Notification {
                private String context;
                private String messages;
                private String type;
            }

            @Data
            public static class LineItemsItem {
                private String addedAt;
                private Variant variant;
                private String id;

                @Data
                public static class Variant {
                    private String sku;
                }
            }

            @Data
            public static class CustomerProfile {
                private String locale;
                private String status;
            }
        }
    }
}