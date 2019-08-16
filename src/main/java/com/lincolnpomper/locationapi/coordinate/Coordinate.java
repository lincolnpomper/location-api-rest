package com.lincolnpomper.locationapi.coordinate;

import com.lincolnpomper.locationapi.location.Location;

public class Coordinate {

	public double latitude;
	public double longitude;

	public Coordinate(double latitude, double longitude) {

		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Coordinate(Location location) {
		this.latitude = location.getLatitude();
		this.longitude = location.getLongitude();
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Coordinate that = (Coordinate) o;

		if (Double.compare(that.latitude, latitude) != 0)
			return false;
		return Double.compare(that.longitude, longitude) == 0;
	}

	@Override public int hashCode() {
		int result;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
}
