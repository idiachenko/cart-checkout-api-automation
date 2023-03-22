package sephora.cartcheckout.jupiter.extension;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import sephora.cartcheckout.jupiter.annotation.GenerateProduct;
import sephora.cartcheckout.jupiter.annotation.TestCaseId;
import sephora.cartcheckout.product.controller.createproduct.CreateProductController;
import sephora.cartcheckout.product.dto.createproduct.response.CreateProductResponseDTO;
import sephora.cartcheckout.utils.Randomizer;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import static sephora.cartcheckout.utils.InputDTOGenerator.prepareCreateProductInputDTO;

public class CreateProductExtension implements BeforeEachCallback, ParameterResolver, AfterEachCallback {

    public static final Namespace PRODUCT_NAMESPACE = Namespace.create(CreateProductExtension.class);
    private final CreateProductController createProductController = new CreateProductController();

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        final String id = getTestId(context);
        final CreateProductResponseDTO product = createProductController.createProduct(prepareCreateProductInputDTO(
                Randomizer.generateSlug(),
                Randomizer.generateSkuNumber(),
                Randomizer.generatePrice()));
        context.getStore(PRODUCT_NAMESPACE).put(id, product);
    }

    @Override
    public void afterEach(final ExtensionContext context) throws Exception {
        GenerateProduct annotation = Arrays.stream(context.getRequiredTestMethod().getParameters())
                .filter(p -> p.isAnnotationPresent(GenerateProduct.class))
                .map(p -> p.getAnnotation(GenerateProduct.class))
                .findFirst()
                .orElseThrow();

        if (annotation.remove()) {
            final String id = getTestId(context);
            var product = context.getStore(PRODUCT_NAMESPACE).get(id, CreateProductResponseDTO.class);
            createProductController.removeItemById(product.getId());
        }
    }

    @Override
    public boolean supportsParameter(final ParameterContext parameterContext,
                                     final ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(CreateProductResponseDTO.class)
                && parameterContext.getParameter().isAnnotationPresent(GenerateProduct.class);
    }

    @Override
    public CreateProductResponseDTO resolveParameter(final ParameterContext parameterContext,
                                                     final ExtensionContext context) throws ParameterResolutionException {
        return context.getStore(PRODUCT_NAMESPACE).get(getTestId(context), CreateProductResponseDTO.class);
    }

    private String getTestId(ExtensionContext context) {
        return Objects.requireNonNull(
                context.getRequiredTestMethod().getAnnotation(TestCaseId.class), "TestCaseId annotation must be."
        ).value();
    }
}
