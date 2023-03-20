package sephora.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import sephora.cartcheckout.graphql.dto.removeitem.RemoveItemMutationRequest;

import java.io.IOException;
import java.nio.file.Paths;

public class TestDataGenerator {

    private static final String FOLDER_WITH_QUERIES = "src/test/resources/graphql/query";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static RemoveItemMutationRequest generateRemoveItemDTO(String skuId, String profileId) throws IOException {
        var request = mapper.readValue(
                Paths.get(FOLDER_WITH_QUERIES,"/removeItem.json").toFile(),
                RemoveItemMutationRequest.class);
        request.setVariables(RemoveItemMutationRequest.Variable.builder()
                .removeItemInput(RemoveItemMutationRequest.RemoveItemInput.builder()
                        .skuId(skuId)
                        .profileId(profileId)
                        .build())
                .build());
        return request;
    }
}
