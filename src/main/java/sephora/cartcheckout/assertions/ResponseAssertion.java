package sephora.cartcheckout.assertions;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import java.sql.SQLException;


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


    public ResponseAssertion isSampleValueIsEqualTo(String customValue) {
        String response = targetResponse.jsonPath().get("path.in.json.response.to.needed.parameter");

        var dspIdAssertionMessage = new ResponseExpectMessages(targetResponse)
                .expectedCustomValue(customValue);

        Assertions.assertThat(response)
                .withFailMessage(dspIdAssertionMessage)
                .isEqualTo(customValue);
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


}
