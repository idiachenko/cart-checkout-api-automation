package sephora.cartcheckout;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseRestAssuredClient {

    public RequestSpecification client(String uri, String basePath) {

        RestAssured.useRelaxedHTTPSValidation();
        return given()
                .baseUri(uri)
                .basePath(basePath)
                .filter(new AllureRestAssured())
                .header("Content-Type", ContentType.JSON)
                .header("Accept", ContentType.JSON)
                .log().all();
    }

}
