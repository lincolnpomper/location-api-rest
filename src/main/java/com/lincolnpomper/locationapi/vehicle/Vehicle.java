package com.lincolnpomper.locationapi.vehicle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity @ApiModel(description = "Represents a vehicle. ") public class Vehicle {

	@Id @ApiModelProperty(notes = "Plate should have at least 5 characters")
	@Size(min = 5, message = "Plate should have at least 5 characters") private String plate;

	public Vehicle() {
	}

	public Vehicle(String plate) {
		this.plate = plate;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}
}
