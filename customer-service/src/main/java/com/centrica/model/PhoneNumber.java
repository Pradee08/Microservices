package com.centrica.model;

import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class PhoneNumber {
	@Length(max = 11)
	private String number;
	private String type;
}
