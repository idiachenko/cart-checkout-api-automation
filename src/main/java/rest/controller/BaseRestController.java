package rest.controller;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseRestController {

    public RequestSpecification restClient(String basePath, Object userInformation) {

        return given()
                .baseUri("our uri from properties")
                .basePath("basepath")
                .filter(new AllureRestAssured())
//                .header("Authorization", "Bearer " + userInformation.getToken())
//                .header("Content-Type", ContentType.JSON)
//                .header("Accept", ContentType.JSON)
//                .header("authorized_entities", userInformation.getUserAuthorizedEntities())
//                .header("authorized_user", userInformation.getUserEmail())
                .log().all();
    }
}
