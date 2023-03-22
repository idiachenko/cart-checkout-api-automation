package sephora.cartcheckout.graphql.dto.removeitem.response;

import lombok.Data;

@Data
public class Audit{
	private String createdAt;
	private String lastModifiedAt;
	private String createdBy;
}