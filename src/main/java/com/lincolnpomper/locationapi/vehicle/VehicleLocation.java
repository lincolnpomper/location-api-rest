package com.lincolnpomper.locationapi.vehicle;

import com.lincolnpomper.locationapi.data.LocationFileData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Entity @ApiModel(description = "Represents a vehicle Location and State. ") public class VehicleLocation {

	private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d y HH:mm:ss zXX").withLocale(Locale.ENGLISH);

	@Id @GeneratedValue private Long id;

	@ManyToOne private Vehicle vehicle;

	@ApiModelProperty @NotNull private LocalDateTime date;
	@ApiModelProperty @NotNull private Integer speed;
	@ApiModelProperty @NotNull private Double latitude;
	@ApiModelProperty @NotNull private Double longitude;
	@ApiModelProperty @NotNull private Boolean ignitionState;

	public VehicleLocation() {
	}

	public VehicleLocation(LocationFileData data, Vehicle vehicle) {
		super();

		this.vehicle = vehicle;

		this.date = LocalDateTime.parse(data.values[0], formatter);
		this.speed = Integer.valueOf(data.values[1]);
		this.latitude = Double.valueOf(data.values[2]);
		this.longitude = Double.valueOf(data.values[3]);
		this.ignitionState = Boolean.parseBoolean(data.values[4]);
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
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

	public Boolean getIgnitionState() {
		return ignitionState;
	}

	public void setIgnitionState(Boolean ignitionState) {
		this.ignitionState = ignitionState;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}
