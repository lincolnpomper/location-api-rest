package com.lincolnpomper.locationapi.coordinate;

public class FindDistance {

	public static double distanceBetween(Coordinate coord1, Coordinate coord2, Unit unit) {

		double dist;

		if ((coord1.latitude == coord2.latitude) && (coord1.longitude == coord2.longitude)) {
			dist = 0.0;
		} else {

			double radianLatitude1 = Math.PI * coord1.latitude / 180;
			double radianLatitude2 = Math.PI * coord2.latitude / 180;
			double theta = coord1.longitude - coord2.longitude;
			double radianTheta = Math.PI * theta / 180;

			dist = Math.sin(radianLatitude1) * Math.sin(radianLatitude2) +
					Math.cos(radianLatitude1) * Math.cos(radianLatitude2) * Math.cos(radianTheta);

			if (dist > 1) {
				dist = 1;
			}

			dist = Math.acos(dist);
			dist = dist * 180 / Math.PI;
			dist = dist * 60 * 1.1515;

			if (unit.equals(Unit.M)) {
				dist = dist * 1.609344 * 1000;
			}
			if (unit.equals(Unit.K)) {
				dist = dist * 1.609344;
			}
			if (unit.equals(Unit.N)) {
				dist = dist * 0.8684;
			}
		}

		return dist;
	}
}
