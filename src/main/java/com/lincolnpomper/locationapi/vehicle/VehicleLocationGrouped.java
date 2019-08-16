package com.lincolnpomper.locationapi.vehicle;

import com.lincolnpomper.locationapi.location.Location;

import java.util.SortedSet;

public class VehicleLocationGrouped {

	public Location location;
	public SortedSet<VehicleLocation> vehicleLocationList;

	public VehicleLocationGrouped(Location location, SortedSet<VehicleLocation> vehicleLocationInsideRadius) {
		this.location = location;
		vehicleLocationList = vehicleLocationInsideRadius;
	}
}
