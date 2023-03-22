package sephora.cartcheckout.product.controller.createproduct;

import io.qameta.allure.Allure;
import io.restassured.specification.RequestSpecification;
import sephora.cartcheckout.product.controller.BaseProductClientController;
import sephora.cartcheckout.product.dto.createproduct.request.CreateProductInputDTO;
import sephora.cartcheckout.product.dto.createproduct.response.CreateProductResponseDTO;

public class CreateProductController extends BaseProductClientController {

    private RequestSpecification createProductClient() {
        return productServiceClient();
    }

    public CreateProductResponseDTO createProduct(CreateProductInputDTO createProductInputDTO) {
        return Allure.step("Create a new product", () -> createProductClient()
                .body(createProductInputDTO)
                .post()
                .then()
                .statusCode(201)
                .extract()
                .body()
                .as(CreateProductResponseDTO.class)
        );
    }

    public void removeItemById(String id) {
        Allure.step(String.format("Remove item by id: '%s'", id), ()-> {
        createProductClient()
                .param("version", "1")
                .delete(id)
                .then().statusCode(200);
        });
    }
}
