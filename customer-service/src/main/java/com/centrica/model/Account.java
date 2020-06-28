package com.centrica.model;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class Account {
	@NotEmpty
	private String id;
	@NotEmpty
	private int customerId;
	@NotEmpty
	@Size(min = 2)
	private String holderName;
	@NotEmpty
	private String type;
	@NotEmpty
	private String status;
	@NotEmpty
	private String paymentType;
	@Valid
	private Tariff tariffDetails;
	private CorrespondenceAddress address;
}
