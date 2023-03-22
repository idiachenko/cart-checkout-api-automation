package sephora.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import sephora.cartcheckout.graphql.dto.additem.AddItemMutationRequest;
import sephora.cartcheckout.graphql.dto.removeitem.RemoveItemMutationRequest;
import sephora.cartcheckout.graphql.enums.Source;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static sephora.cartcheckout.graphql.dto.additem.AddItemMutationRequest.*;

public class TestDataGenerator {

    private static final String FOLDER_WITH_QUERIES = "src/test/resources/graphql/query";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static RemoveItemMutationRequest generateRemoveItemDTO(String skuId, String profileId) {
        RemoveItemMutationRequest request;
        try {
            request = mapper.readValue(
                    Paths.get(FOLDER_WITH_QUERIES, "/removeItem.json").toFile(),
                    RemoveItemMutationRequest.class);
            request.setVariables(RemoveItemMutationRequest.Variable.builder()
                    .removeItemInput(RemoveItemMutationRequest.RemoveItemInput.builder()
                            .skuId(skuId)
                            .profileId(profileId)
                            .build())
                    .build());
            return request;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static AddItemMutationRequest generateAddItemDTO(List<String> skuIds, Source source) {
        AddItemMutationRequest request;
        try {
            request = mapper.readValue(
                    Paths.get(FOLDER_WITH_QUERIES, "/addItemToShoppingList.json").toFile(),
                    AddItemMutationRequest.class);
            request.setVariables(Variable
                    .builder()
                    .input(Input.builder()
                            .skus(skuIds)
                            .source(source)
                            .build())
                    .build());
            return request;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
