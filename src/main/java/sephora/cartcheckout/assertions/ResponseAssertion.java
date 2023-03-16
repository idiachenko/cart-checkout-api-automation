package sephora.cartcheckout.assertions;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;


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

        var dspIdAssertionMessage = new ResponseExpectMessages(targetResponse)
                .expectedShoppingListKey(expected);

        Assertions.assertThat(actual)
                .withFailMessage(dspIdAssertionMessage)
                .isEqualTo(expected + suffixThatAddedToKeyInResponse);
    }
}
