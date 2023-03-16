package sephora.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sephora.cartcheckout.rest.controller.GetShoppingListController;

public class SampleRestTest {

    static GetShoppingListController getShoppingListController = new GetShoppingListController();


    @Test
    public void sample2(){
//        Object preparedData = ""; // here we have DTO or POJO with prepared data
//        //then we can use 2 ways in order to verify the endpoint
//        getShoppingListController.createNewShoppingList(preparedData) // <-- here endpoint is triggered and response is returned
//                .statusCodeIsEqualTo(ResponseExpectMessages.StatusCode.OK); // <-- and here we already start verifying the reesponse
//                .isSampleValueIsEqualTo("");
//
//        // or we can get ResponseAssertion object in separate class
//        ResponseAssertion  getShoppingListResponse = getShoppingListController.createNewShoppingList(preparedData);
//        //and do assertions
//        getShoppingListResponse.statusCodeIsEqualTo(ResponseExpectMessages.StatusCode.OK);
//                .isSampleValueIsEqualTo("value");
        Assertions.assertTrue(true);
    }


    /**
     *
     *
     * TODO:
     * - Create few integration tests per each service: rest and graphql
     * - add property configurator and way to use it
     * - add normal logger
     * - update git ignore
     * - think about generation of DTOs for rest (openApi can be used)
     * - think about graphql tests implementation, could be in same maner
     * - think about login or token generation (I believe that we will get such ability soon,
     *   and should be able to generate tokens
     * - think about before/after beforeAll/AfterAll methods
     * - think about parallel run (on late stage whne we have some working tests)
     * - think about test suits for run, e.g. smoke/regression integration/end2end
     * - ci/cd support (for late stage)
     *
     */

}
