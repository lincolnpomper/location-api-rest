package com.lincolnpomper.locationapi.coordinate;

import com.lincolnpomper.locationapi.coordinate.Coordinate;
import com.lincolnpomper.locationapi.coordinate.FindDistance;
import com.lincolnpomper.locationapi.coordinate.Unit;
import org.junit.Assert;
import org.junit.Test;

public class TestFindDistance {

	private final static Coordinate POI = new Coordinate(-25.4354048, -50.7169816);
	private final static Coordinate VEHICLE_POSITION_1 = new Coordinate(-25.4387571, -50.71375);
	private final static Coordinate VEHICLE_POSITION_2 = new Coordinate(-25.4311558, -50.7314701);

	@Test public void coordinateShouldBeInside600Meters() {

		double distance = FindDistance.distanceBetween(POI, VEHICLE_POSITION_1, Unit.M);

		Assert.assertTrue(distance < 600);
	}

	@Test public void coordinateShouldNotBeInside600Meters() {

		double distance = FindDistance.distanceBetween(POI, VEHICLE_POSITION_2, Unit.M);

		Assert.assertFalse(distance < 600);
	}

	@Test public void coordinateShouldNotBeInside400Meters() {

		double distance = FindDistance.distanceBetween(POI, VEHICLE_POSITION_1, Unit.M);

		Assert.assertFalse(distance < 400);
	}
}