package sephora.cartcheckout.product.dto.createproduct.response;

import lombok.Data;

@Data
public class CreatedBy{
	private String clientId;
	private Boolean isPlatformClient;
}