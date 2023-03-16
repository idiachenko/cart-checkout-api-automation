package sephora.cartcheckout.graphql.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum ShoppingListSortType {
    DEFAULT("DEFAULT"),

    RECENTLY(
            "RECENTLY"),

    BRANDNAME_ASC(
            "BRANDNAME_ASC"),

    BRANDNAME_DESC(
            "BRANDNAME_DESC"),

    PRICE_LOW_TO_HIGH(
            "PRICE_LOW_TO_HIGH"),

    PRICE_HIGH_TO_LOW(
            "PRICE_HIGH_TO_LOW"),

    STOREAVAILABILITY("STOREAVAILABILITY");

    private final String value;

    public static ShoppingListSortType getByValue(String sortType) {
        return Arrays.stream(ShoppingListSortType.values())
                .filter(type -> type.value.equals(sortType))
                .findFirst()
                .orElse(DEFAULT);
    }
}
