package com.lincolnpomper.locationapi.location;

import com.lincolnpomper.locationapi.data.LocationFileData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity @ApiModel(description = "Represents a POI, location of interest. ") public class Location {

	@Id @ApiModelProperty(notes = "Name should have at least 1 character") @Size(min = 1, message = "Name should have at least 1 character")
	private String name;

	@ApiModelProperty @NotNull private Double radius;

	@ApiModelProperty @NotNull private Double latitude;

	@ApiModelProperty @NotNull private Double longitude;

	public Location() {
	}

	public Location(LocationFileData data) {
		super();
		this.name = data.name;
		this.radius = Double.valueOf(data.values[0]);
		this.latitude = Double.valueOf(data.values[1]);
		this.longitude = Double.valueOf(data.values[2]);
	}

	public Location(String name, Double radius, Double latitude, Double longitude) {
		super();
		this.name = name;
		this.radius = radius;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getRadius() {
		return radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
