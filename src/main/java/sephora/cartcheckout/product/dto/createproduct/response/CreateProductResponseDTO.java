package sephora.cartcheckout.product.dto.createproduct.response;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateProductResponseDTO {
	private String versionModifiedAt;
	private String createdAt;
	private MasterData masterData;
	private String lastModifiedAt;
	private CreatedBy createdBy;
	private LastModifiedBy lastModifiedBy;
	private String id;
	private int version;
	private int lastMessageSequenceNumber;
	private int lastVariantId;
	private ProductType productType;
}