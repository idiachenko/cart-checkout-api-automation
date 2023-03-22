package sephora.graphql;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sephora.cartcheckout.graphql.controller.GraphQLShoppingListController;
import sephora.cartcheckout.jupiter.annotation.GenerateProduct;
import sephora.cartcheckout.jupiter.annotation.TestCaseId;
import sephora.cartcheckout.product.dto.createproduct.response.CreateProductResponseDTO;
import sephora.utility.TestDataGenerator;

import java.io.IOException;
import java.util.List;

import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static sephora.cartcheckout.graphql.enums.Source.PRODUCT_PAGE;


@Tag("graphql")
@Feature("Shopping List")
@DisplayName("Remove Item Suite")
class GraphQLRemoveItemTest extends BaseGraphQlTest {

    private static final String SHOPPING_LIST_NAME = "denis";
    private final GraphQLShoppingListController graphQLShoppingList = new GraphQLShoppingListController();

    @Test
    @Story("Remove Item from Shopping List")
    @TmsLink("13633991")
    @Severity(BLOCKER)
    @Tag("smoke")
    @TestCaseId("13633991")
    @DisplayName("Verify the Sku is removed from the shopping list successfully")
    void verifySKURemovedFromShoppingListWithValidProfileIdAndSku(@GenerateProduct CreateProductResponseDTO product) throws IOException {
        final var skuId = product.getMasterData().getCurrent().getMasterVariant().getSku();
        Allure.parameter("SKU ID", skuId);
        Allure.parameter("Profile ID", SHOPPING_LIST_NAME);

        var addRequest = TestDataGenerator.generateAddItemDTO(List.of(skuId), PRODUCT_PAGE);
        graphQLShoppingList.addItemToShoppingList(addRequest);

        var removeItemRequest = TestDataGenerator.generateRemoveItemDTO(skuId, SHOPPING_LIST_NAME);
        graphQLShoppingList.removeItemFromShoppingList(removeItemRequest)
                .verifySkuRemovedFromShoppingList(skuId);
    }

    @Test
    @Story("Remove Item from Shopping List")
    @Severity(NORMAL)
    @TmsLink("13633993")
    @TestCaseId("13633993")
    @DisplayName("Verify deleting the last item in the shopping list with valid values")
    void verifyDeletingTheLastItemInTheShoppingListWithValidValues(@GenerateProduct CreateProductResponseDTO product) {
        final var skuId = product.getMasterData().getCurrent().getMasterVariant().getSku();
        Allure.parameter("SKU ID", skuId);
        Allure.parameter("Profile ID", SHOPPING_LIST_NAME);

        var addRequest = TestDataGenerator.generateAddItemDTO(List.of(skuId), PRODUCT_PAGE);
        graphQLShoppingList.addItemToShoppingList(addRequest);

        var removeItemRequest = TestDataGenerator.generateRemoveItemDTO(skuId, SHOPPING_LIST_NAME);
        graphQLShoppingList.removeItemFromShoppingList(removeItemRequest)
                .verifySkuRemovedFromShoppingList(skuId);
    }

    @Test
    @Story("Remove Item from Shopping List")
    @TmsLink("13633995")
    @Severity(NORMAL)
    @TestCaseId("13633995")
    @DisplayName("Verify remove of  item from the shopping list using incorrect sku number")
    void verifyRemoveOfItemFromTheShoppingListUsingIncorrectSkuNumber() {
        final String skuId = String.valueOf(faker.number().randomNumber());
        Allure.parameter("SKU ID", skuId);
        Allure.parameter("Profile ID", SHOPPING_LIST_NAME);

        var removeItemRequest = TestDataGenerator.generateRemoveItemDTO(skuId, SHOPPING_LIST_NAME);
        graphQLShoppingList.removeItemFromShoppingList(removeItemRequest)
                .validateErrorMessage(String.format("There is no such shoppingListItem: %s", skuId));
    }

    @Test
    @Story("Remove Item from Shopping List")
    @TmsLink("13633996")
    @Severity(NORMAL)
    @TestCaseId("13633996")
    @DisplayName("Verify remove of item from the shopping list which was already deleted")
    void VerifyRemoveOfItemFromTheShoppingListWhichWasAlreadyDeleted() {
        final String skuId = "P454207-2301365";
        Allure.parameter("SKU ID", skuId);
        Allure.parameter("Profile ID", SHOPPING_LIST_NAME);

        var removeItemRequest = TestDataGenerator.generateRemoveItemDTO(skuId, SHOPPING_LIST_NAME);
        graphQLShoppingList.removeItemFromShoppingList(removeItemRequest)
                .validateErrorMessage(String.format("There is no such shoppingListItem: %s", skuId));
    }

    @Test
    @Story("Remove Item from Shopping List")
    @Severity(NORMAL)
    @TmsLink("13634000")
    @TestCaseId("13634000")
    @DisplayName("Verify deletion of item from shopping list using incorrect profileId")
    void verifyDeletionOfItemFromShoppingListUsingIncorrectProfileId() {
        final String skuId = "P454207-2301364";
        final String profileId = "63123dsadsad";
        Allure.parameter("SKU ID", skuId);
        Allure.parameter("Profile ID", profileId);

        var removeItemRequest = TestDataGenerator.generateRemoveItemDTO(skuId, profileId);
        graphQLShoppingList.removeItemFromShoppingList(removeItemRequest)
                .validateErrorMessage(String.format("Shopping list with id: [%s-shopping-list] was not found.", profileId));
    }
}
