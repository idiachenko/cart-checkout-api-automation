package sephora.cartcheckout.assertions;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import java.util.List;


public class ResponseAssertion {

    private final Response targetResponse;

    public ResponseAssertion(Response targetResponse) {
        this.targetResponse = targetResponse;
    }


    public ResponseAssertion statusCodeIsEqualTo(ResponseExpectMessages.StatusCode expectedStatusCode) {
        var statusCodeAssertionMessage = new ResponseExpectMessages(targetResponse)
                .expectedStatusCode(expectedStatusCode);

        Assertions.assertThat(targetResponse.statusCode())
                .withFailMessage(statusCodeAssertionMessage)
                .isEqualTo(expectedStatusCode.code);
        return this;
    }

    public void responseIsEmpty() {
        Assertions.assertThat(targetResponse.asString()).isEmpty();
    }

    public void responseIsEmptyArray() {
        Assertions.assertThat(targetResponse.asString()).isEqualTo("[]");
    }

    public Response getTargetResponse() {
        return targetResponse;
    }


    public void keyValueEqualsTo(String expected) {
        String actual = targetResponse.jsonPath().get("data.shoppingList.key");
        String suffixThatAddedToKeyInResponse = "-shopping-list";

        var expectedShoppingListKey = new ResponseExpectMessages(targetResponse)
                .expectedShoppingListKey(expected);

        Assertions.assertThat(actual)
                .withFailMessage(expectedShoppingListKey)
                .isEqualTo(expected + suffixThatAddedToKeyInResponse);
    }

    public void errorMessageContains(String errorMessage) {
        List<String> errors = targetResponse.jsonPath().get("errors.message");

        var expectedErrorMessage = new ResponseExpectMessages(targetResponse)
                .expectedErrorMessage(errorMessage);

        Assertions.assertThat(errors)
                .withFailMessage(expectedErrorMessage)
                .contains(errorMessage);
    }

    public ResponseAssertion validateErrorMessage(String expected) {
        String actualMessage = targetResponse.jsonPath().get("errors[0].message");
        Assertions.assertThat(actualMessage).isEqualTo(expected);
        return this;
    }
}
