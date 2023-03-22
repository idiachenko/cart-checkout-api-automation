package sephora.cartcheckout.graphql.dto.removeitem.response;

import lombok.Data;

@Data
public class Customer{
	private String firstName;
	private String lastName;
	private Object middleName;
	private String id;
	private String email;
}