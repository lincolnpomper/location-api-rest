package com.lincolnpomper.locationapi.vehicle;

import com.lincolnpomper.locationapi.location.Location;

public class VehicleLocationTimeVO {

	private Vehicle vehicle;

	private Location location;

	private Long timeSpent;

	public VehicleLocationTimeVO(Vehicle vehicle, Location location, Long timeSpent) {
		this.vehicle = vehicle;
		this.location = location;
		this.timeSpent = timeSpent;
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

	public Long getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(Long timeSpent) {
		this.timeSpent = timeSpent;
	}
}
