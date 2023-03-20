package sephora.cartcheckout.product.dto.createproduct.response;

import lombok.Data;

@Data
public class MasterData{
	private Current current;
	private Staged staged;
	private boolean published;
	private boolean hasStagedChanges;
}