package com.lincolnpomper.locationapi.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController public class VehicleController {

	@Autowired private VehicleRepository vehicleRepository;

	@GetMapping("/vehicles") public Iterable<Vehicle> retrieveAllVehicles() {
		return vehicleRepository.findAll();
	}

}
