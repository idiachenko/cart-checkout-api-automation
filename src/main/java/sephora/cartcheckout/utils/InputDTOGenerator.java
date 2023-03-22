package sephora.cartcheckout.utils;

import sephora.cartcheckout.product.controller.createproduct.CreateProductController;
import sephora.cartcheckout.product.dto.createproduct.request.CreateProductInputDTO;
import sephora.cartcheckout.product.dto.createproduct.response.CreateProductResponseDTO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InputDTOGenerator {

    static CreateProductController createProductController = new CreateProductController();

    public static CreateProductInputDTO prepareCreateProductInputDTO(String slug, String sku, int centAmount) throws IOException {
        CreateProductInputDTO createProductInputDTO = new CreateProductInputDTO();
        createProductInputDTO = JsonUtils.getJsonAsObject(createProductInputDTO, "src/main/resources/productJson/createProductInput.json");
        createProductInputDTO.getSlug().setEn(slug);
        createProductInputDTO.getName().setEn("API test " + System.currentTimeMillis());
        createProductInputDTO.getMasterVariant().setSku(String.format("%s-%s", slug, sku));
        createProductInputDTO.getMasterVariant().getPrices().get(0).getValue().setCentAmount(centAmount);
        createProductInputDTO.getMasterVariant().getPrices().get(0).getValue().setCurrencyCode("USD");

        return createProductInputDTO;

    }

    public static Map<String, String> getMapIdAndSkuOfNewlyAddedProducts(int number) throws IOException {
        Map<String, String> map = new HashMap<>();
        while (number > 0) {

            CreateProductResponseDTO product = createProductController.createProduct(
                    prepareCreateProductInputDTO(
                            Randomizer.generateSlug(),
                            Randomizer.generateSkuNumber(),
                            Randomizer.generatePrice()));

            map.put(product.getId(), product.getMasterData().getCurrent().getMasterVariant().getSku());
            number--;

        }
        return map;


    }


}
