package sephora.cartcheckout.graphql.dto.removeitem.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RemoveItemMutationResponse {

	@JsonProperty("data")
	private ResponseDataField data;
}