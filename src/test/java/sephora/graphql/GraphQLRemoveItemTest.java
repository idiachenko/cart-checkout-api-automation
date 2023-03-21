package sephora.graphql;

import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sephora.cartcheckout.graphql.controller.RemoveItemController;
import sephora.cartcheckout.graphql.dto.removeitem.response.GetShoppingListResponse;
import sephora.cartcheckout.graphql.dto.removeitem.response.LineItemsItem;
import sephora.cartcheckout.graphql.dto.removeitem.response.Product;
import sephora.cartcheckout.jupiter.annotation.TestCaseId;
import sephora.utility.TestDataGenerator;

import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@Feature("Shopping List")
@DisplayName("Remove Item Suite")
class GraphQLRemoveItemTest extends BaseGraphQlTest {

    private static final String SHOPPING_LIST_NAME = "denis";
    private final RemoveItemController removeItemController = new RemoveItemController();

    @Test
    @Story("Remove Item from Shopping List")
    @Disabled
    @TmsLink("13633991")
    @Severity(BLOCKER)
    @TestCaseId("13633991")
    @DisplayName("Verify the Sku is removed from the shopping list successfully")
    void verifySKURemovedFromShoppingListWithValidProfileIdAndSku() {
        final String skuId = "P421275-1962141";
        Allure.parameter("SKU ID", skuId);
        Allure.parameter("Profile ID", SHOPPING_LIST_NAME);

        var removeItemRequest = TestDataGenerator.generateRemoveItemDTO(skuId, SHOPPING_LIST_NAME);
        var collect = removeItemController.removeItemFromShoppingList(removeItemRequest)
                .getTargetResponse()
                .then()
                .extract().as(GetShoppingListResponse.class)
                .getData()
                .getRemoveItem()
                .getLineItems()
                .stream()
                .map(LineItemsItem::getProduct)
                .map(Product::getSku)
                .collect(toList());

        Assertions.assertAll(
                () -> assertEquals(1, collect.size()),
                () -> assertNotEquals(skuId, collect.stream().findFirst().get().getId())
        );
    }

    @Test
    @Story("Remove Item from Shopping List")
    @Disabled
    @Severity(NORMAL)
    @TmsLink("13633993")
    @TestCaseId("13633993")
    @DisplayName("Verify deleting the last item in the shopping list with valid values")
    void verifyDeletingTheLastItemInTheShoppingListWithValidValues() {
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
    void verifyRemoveOfItemFromTheShoppingListUsingIncorrectSkuNumber() {
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
    void VerifyRemoveOfItemFromTheShoppingListWhichWasAlreadyDeleted() {
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
    void verifyDeletionOfItemFromShoppingListUsingIncorrectProfileId() {
        final String skuId = "P454207-2301364";
        final String profileId = "63123dsadsad";
        Allure.parameter("SKU ID", skuId);
        Allure.parameter("Profile ID", profileId);


        var removeItemRequest = TestDataGenerator.generateRemoveItemDTO(skuId, profileId);
        removeItemController.removeItemFromShoppingList(removeItemRequest)
                .validateErrorMessage(String.format("Shopping list with id: [%s-shopping-list] was not found.", profileId));
    }
}
