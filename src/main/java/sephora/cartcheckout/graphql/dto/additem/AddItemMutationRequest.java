package sephora.cartcheckout.graphql.dto.additem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sephora.cartcheckout.graphql.enums.Source;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddItemMutationRequest {
    private String query;
    private String operationName;
    private Variable variables;

    @Builder
    public static class Variable {
        public Input input;
    }


    @Builder
    public static class Input {
        public List<String> skus;
        public Source source;
    }
}
