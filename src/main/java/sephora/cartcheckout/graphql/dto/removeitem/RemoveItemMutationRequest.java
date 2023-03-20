package sephora.cartcheckout.graphql.dto.removeitem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemoveItemMutationRequest {
    private String query;
    private String operationName;
    private Variable variables;

    @Builder
     public static class RemoveItemInput {
        public String skuId;
        public String profileId;
    }

    @Builder
    public static class Variable {
        public RemoveItemInput removeItemInput;
    }
}
