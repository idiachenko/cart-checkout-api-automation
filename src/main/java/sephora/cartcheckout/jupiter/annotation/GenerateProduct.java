package sephora.cartcheckout.jupiter.annotation;


import org.junit.jupiter.api.extension.ExtendWith;
import sephora.cartcheckout.jupiter.extension.CreateProductExtension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@ExtendWith(CreateProductExtension.class)
public @interface GenerateProduct {

    /**
     * Removes the product after the test has completed if set to true.
     *
     * @return by default the flag is set to true.
     */
    boolean remove() default true;
}
