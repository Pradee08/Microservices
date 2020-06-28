package com.centrica.model;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Customer {
	
	private Integer id;
	@NotEmpty
	private String ucrn;
	@NotEmpty
	private String title;
	@NotEmpty
	@Size(min=2)
	private String firstName;
	@NotEmpty
	private String lastname;
	private String dateOfBirth;
	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	private List<String> energyAccounts;
	@Valid
	private List<PhoneNumber>telephoneNumbers;
	private CorrespondenceAddress correspondenceAddress;

}
