package sephora.graphql;

import io.qameta.allure.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sephora.cartcheckout.graphql.controller.RemoveItemController;
import sephora.cartcheckout.jupiter.annotation.TestCaseId;
import sephora.utility.TestDataGenerator;

import java.io.IOException;

import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.NORMAL;


@Feature("Shopping List")
@DisplayName("Remove Item Suite")
class GraphQLRemoveItemTest extends BaseGraphQlTest {

    private static final String SHOPPING_LIST_NAME = "denis";
    private final RemoveItemController removeItemController = new RemoveItemController();

    @Test
    @Disabled
    @Story("Remove Item from Shopping List")
    @TmsLink("13633991")
    @Severity(BLOCKER)
    @TestCaseId("13633991")
    @DisplayName("Verify the Sku is removed from the shopping list successfully")
    void verifySKURemovedFromShoppingListWithValidProfileIdAndSku() throws IOException {
        Allure.parameter("SKU ID", "");
        Allure.parameter("Profile ID", SHOPPING_LIST_NAME);

        // TODO: Create a shopping list for the test
        // TODO: add SKU to the shopping list


        // TODO: Send Remove Item request
        var removeItemRequest = TestDataGenerator.generateRemoveItemDTO("d", SHOPPING_LIST_NAME);
        removeItemController.removeItemFromShoppingList(removeItemRequest)
                .validateErrorMessage("There is no such shoppingListItem: d");

        // TODO: Validate the result
    }

    @Test
    @Story("Remove Item from Shopping List")
    @Disabled
    @Severity(NORMAL)
    @TmsLink("13633993")
    @TestCaseId("13633993")
    @DisplayName("Verify deleting the last item in the shopping list with valid values")
    void verifyDeletingTheLastItemInTheShoppingListWithValidValues() throws IOException {
        Allure.parameter("Profile ID", SHOPPING_LIST_NAME);


        var removeItemRequest = TestDataGenerator.generateRemoveItemDTO("d", SHOPPING_LIST_NAME);
        removeItemController.removeItemFromShoppingList(removeItemRequest);
    }

    @Test
    @Story("Remove Item from Shopping List")
    @TmsLink("13633995")
    @Severity(NORMAL)
    @TestCaseId("13633995")
    @DisplayName("Verify remove of  item from the shopping list using incorrect sku number")
    void verifyRemoveOfItemFromTheShoppingListUsingIncorrectSkuNumber() throws IOException {
        final String skuId = String.valueOf(faker.number().randomNumber());
        Allure.parameter("SKU ID", skuId);
        Allure.parameter("Profile ID", SHOPPING_LIST_NAME);


        var removeItemRequest = TestDataGenerator.generateRemoveItemDTO(skuId, SHOPPING_LIST_NAME);
        removeItemController.removeItemFromShoppingList(removeItemRequest)
                .validateErrorMessage(String.format("There is no such shoppingListItem: %s", skuId));
    }

    @Test
    @Story("Remove Item from Shopping List")
    @TmsLink("13633996")
    @Severity(NORMAL)
    @TestCaseId("13633996")
    @DisplayName("Verify remove of item from the shopping list which was already deleted")
    void VerifyRemoveOfItemFromTheShoppingListWhichWasAlreadyDeleted() throws IOException {
        final String skuId = "P454207-2301365";
        Allure.parameter("SKU ID", skuId);
        Allure.parameter("Profile ID", SHOPPING_LIST_NAME);


        var removeItemRequest = TestDataGenerator.generateRemoveItemDTO(skuId, SHOPPING_LIST_NAME);
        removeItemController.removeItemFromShoppingList(removeItemRequest)
                .validateErrorMessage(String.format("There is no such shoppingListItem: %s", skuId));
    }

    @Test
    @Story("Remove Item from Shopping List")
    @Severity(NORMAL)
    @TmsLink("13634000")
    @TestCaseId("13634000")
    @DisplayName("Verify deletion of item from shopping list using incorrect profileId")
    void verifyDeletionOfItemFromShoppingListUsingIncorrectProfileId() throws IOException {
        final String skuId = "P454207-2301364";
        final String profileId = "63123dsadsad";
        Allure.parameter("SKU ID", skuId);
        Allure.parameter("Profile ID", profileId);


        var removeItemRequest = TestDataGenerator.generateRemoveItemDTO(skuId, profileId);
        removeItemController.removeItemFromShoppingList(removeItemRequest)
                .validateErrorMessage(String.format("Shopping list with id: [%s-shopping-list] was not found.", profileId));
    }
}
