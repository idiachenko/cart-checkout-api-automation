package sephora.cartcheckout.graphql.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShoppingListSortType {

    RECENTLY("RECENTLY"),

    BRANDNAME_ASC("BRANDNAME_ASC"),

    BRANDNAME_DESC("BRANDNAME_DESC"),

    PRICE_LOW_TO_HIGH("PRICE_LOW_TO_HIGH"),

    PRICE_HIGH_TO_LOW("PRICE_HIGH_TO_LOW"),

    STOREAVAILABILITY("STOREAVAILABILITY"),

    INCORRECT("INCORRECT");

    private final String value;


}
