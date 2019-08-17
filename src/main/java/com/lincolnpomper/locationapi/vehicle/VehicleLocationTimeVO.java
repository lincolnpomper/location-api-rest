package com.lincolnpomper.locationapi.vehicle;

import com.lincolnpomper.locationapi.location.Location;

public class VehicleLocationTimeVO {

	private Vehicle vehicle;

	private Location location;

	private Long timeSpentInMinutes;

	public VehicleLocationTimeVO(Vehicle vehicle, Location location, Long timeSpentInMinutes) {
		this.vehicle = vehicle;
		this.location = location;
		this.timeSpentInMinutes = timeSpentInMinutes;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Long getTimeSpentInMinutes() {
		return timeSpentInMinutes;
	}

	public void setTimeSpentInMinutes(Long timeSpentInMinutes) {
		this.timeSpentInMinutes = timeSpentInMinutes;
	}
}
