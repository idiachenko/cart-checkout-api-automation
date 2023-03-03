package sephora.cartcheckout.assertions;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * String Builder Builder
 */
public class StringBuilderCustom {

    private final StringBuilder strBuilder = new StringBuilder();

    public static StringBuilderCustom sbb() {
        return new StringBuilderCustom();
    }

    public static StringBuilderCustom sbb(Object basePlainText) {
        return sbb().append(basePlainText);
    }

    private StringBuilderCustom addToBuild(Object targetPlainText) {
        this.strBuilder.append(targetPlainText);
        return this;
    }

    /**
     * Append plain text
     */
    public StringBuilderCustom append(Object targetPlainText) {
        return this.addToBuild(targetPlainText);
    }

    /**
     * Append plain text
     */
    public StringBuilderCustom append(Field[] targetPlainText) {
        var collect = Arrays.stream(targetPlainText)
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .map(field -> sbb()
                        .dQuote(field.getName()).append(':').w()
                        .dQuote(field.getType().getSimpleName())
                        .bld())
                .collect(Collectors.toList());

        return this.append(collect);
    }

    /**
     * Add new line caret
     */
    public StringBuilderCustom n() {
        return this.append("\n");
    }

    /**
     * Add new tab
     */
    public StringBuilderCustom t() {
        return this.append("\t");
    }

    /**
     * Add new whitespace
     */
    public StringBuilderCustom w() {
        return this.append(" ");
    }

    /**
     * Add a coma
     */
    public StringBuilderCustom coma() {
        return this.append(',');
    }

    /**
     * append as single quoted value
     *
     * @return 'targetPlainText'
     */
    public StringBuilderCustom sQuoted(Object targetPlainText) {
        return this.append("'").append(targetPlainText).append("'");
    }

    /**
     * append as double quoted value
     *
     * @return "targetPlainText"
     */
    public StringBuilderCustom dQuote(Object targetPlainText) {
        return this.append('\"').append(targetPlainText).append('\"');
    }

    /**
     * append as double quoted value
     *
     * @return "targetPlainText"
     */
    public StringBuilderCustom arrayBrackets(Object targetPlainText) {
        return this.append('[').append(targetPlainText).append(']');
    }

    public StringBuilderCustom slash() {
        return this.append('/');
    }

    public String build() {
        return this.strBuilder.toString();
    }

    public String bld() {
        return build();
    }

    @Override
    public String toString() {
        return bld();
    }

}
