package com.lincolnpomper.locationapi.vehicle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestVehicleLocationRepository {

	private static final int QUANTITY = 722;
	private static final String PLATE = "TESTE001";

	@Autowired private VehicleLocationRepository repository;

	@Test public void shouldFindAllVehicles() {

		final List<VehicleLocation> list = repository.findAllByVehicle(PLATE);

		assertEquals(QUANTITY, list.size());
		assertEquals(PLATE, list.get(0).getVehicle().getPlate());
	}
}
