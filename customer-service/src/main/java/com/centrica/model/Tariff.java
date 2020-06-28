package com.centrica.model;

import java.util.Date;
import javax.validation.constraints.Future;
import lombok.Data;

@Data
public class Tariff {
	private String tariffName;
	private String supplierName;
	private String cancellationCharge;
	@Future
	private Date endDate;
	private Double unitRate;
	private Double standingCharge;
	private Double personalProjection;
	private Double estimatedAnnualConsumption;
}
