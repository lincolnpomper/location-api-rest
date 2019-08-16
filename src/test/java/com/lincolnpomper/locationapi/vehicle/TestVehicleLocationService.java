package com.lincolnpomper.locationapi.vehicle;

import com.lincolnpomper.locationapi.coordinate.Coordinate;
import com.lincolnpomper.locationapi.coordinate.FindDistance;
import com.lincolnpomper.locationapi.coordinate.Unit;
import com.lincolnpomper.locationapi.vehicle.VehicleLocation;
import com.lincolnpomper.locationapi.vehicle.VehicleLocationController;
import com.lincolnpomper.locationapi.vehicle.VehicleLocationGrouped;
import com.lincolnpomper.locationapi.vehicle.VehicleLocationService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.stream.StreamSupport;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestVehicleLocationService {

	private final static String VEHICLE_1 = "TESTE001";
	private final static String VEHICLE_2 = "CAR0012";
	private final static Coordinate POI_2 = new Coordinate(-25.568056, -51.480278);

	private final static LocalDateTime START_DATE = LocalDateTime.of(2018, 1, 1, 0, 0);
	private final static LocalDateTime END_DATE = LocalDateTime.of(2019, 12, 1, 0, 0);

	@Autowired private VehicleLocationService vehicleLocationService;

	@BeforeClass public static void setup() {
	}

	@Test public void shouldFind65LocationsFromVehicle1ForPOI_2() {

		SortedSet<VehicleLocationGrouped> list =
				vehicleLocationService.getLocationsByPlateAndDateGroupedByPoi(VEHICLE_1, START_DATE, END_DATE);

		int quantity = 0;

		final VehicleLocationGrouped vehicle =
				list.stream().filter(item -> new Coordinate(item.location).equals(POI_2)).findFirst().orElse(null);
		if (vehicle != null) {
			quantity = vehicle.vehicleLocationList.size();
		}

		Assert.assertEquals(65, quantity);
	}

	@Test public void shouldFind170MinutesForVehicle1AndPOI_2() {

		SortedSet<VehicleLocationGrouped> list =
				vehicleLocationService.getLocationsByPlateAndDateGroupedByPoi(VEHICLE_1, START_DATE, END_DATE);

		long minutes = 0;

		final VehicleLocationGrouped vehicle =
				list.stream().filter(item -> new Coordinate(item.location).equals(POI_2)).findFirst().orElse(null);

		if (vehicle != null) {
			LocalDateTime startDate =
					vehicle.vehicleLocationList.stream().min(Comparator.comparing(VehicleLocation::getDate)).get().getDate();
			LocalDateTime endDate =
					vehicle.vehicleLocationList.stream().max(Comparator.comparing(VehicleLocation::getDate)).get().getDate();

			minutes = Duration.between(startDate, endDate).getSeconds() / 60;
		}

		Assert.assertEquals(170, minutes);
	}
}