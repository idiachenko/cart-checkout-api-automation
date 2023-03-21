package sephora.cartcheckout.graphql.dto.removeitem.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.Data;

@Data
public class GetShoppingListResponse {

	@JsonProperty("data")
	private ResponseDataField data;
}