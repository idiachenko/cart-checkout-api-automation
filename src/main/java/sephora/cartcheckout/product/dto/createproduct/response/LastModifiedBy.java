package sephora.cartcheckout.product.dto.createproduct.response;

import lombok.Data;

@Data
public class LastModifiedBy{
	private String clientId;
	private Boolean isPlatformClient;
}