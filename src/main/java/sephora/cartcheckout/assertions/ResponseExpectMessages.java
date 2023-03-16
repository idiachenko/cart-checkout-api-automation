package sephora.cartcheckout.assertions;

import io.restassured.response.Response;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;


public class ResponseExpectMessages {

    private final Response targetResponse;

    public ResponseExpectMessages(Response targetResponse) {
        this.targetResponse = targetResponse;
    }

    public String expectedStatusCode(StatusCode expectedStatusCode) {
        return StringBuilderCustom.sbb().n()
                .append("Expected status code:").w().append(expectedStatusCode.name()).w().append(expectedStatusCode.code).n()
                .append("Actual status code:").w().append(StatusCode.byValue(targetResponse.statusCode())).w().sQuoted(targetResponse.statusCode()).n()
                .append("Actual response body:").n()
                .append(targetResponse.body().asString()).n()
                .bld();
    }

    public String expectedShoppingListKey(String expected) {
        return StringBuilderCustom.sbb().n()
                .append("Expected shoppingList key:").w().append(expected).n()
                .append("Actual response body:").n()
                .append(targetResponse.body().asString()).n()
                .bld();
    }


    @AllArgsConstructor
    public enum StatusCode {

        CREATED(201), OK(200), ACCEPTED(202), NO_CONTENT(204),
        REDIRECT(302),
        BAD_REQUEST(400), UNAUTHORIZED(401), FORBIDDEN(403), NOT_FOUND(404), PROXY_AUTH_REQUIRED(407), CONFLICT(409), TOO_LARGE(413),
        SERVER_ERROR(500), SERVICE_UNAVAILABLE(503), GATEWAY_TIMEOUT(504);

        public int code;

        public static StatusCode byValue(int value) {
            return Stream.of(StatusCode.values())
                    .filter(code -> code.code == value).findFirst()
                    .orElseThrow(() -> new RuntimeException(StringBuilderCustom.sbb("No such status code known").w().append(value).bld()));
        }
    }
}

