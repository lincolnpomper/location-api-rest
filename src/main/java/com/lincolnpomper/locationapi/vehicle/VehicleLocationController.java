package com.lincolnpomper.locationapi.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController public class VehicleLocationController {

	@Autowired private VehicleLocationRepository vehicleLocationRepository;

	@GetMapping("/vehicles/{vehiclePlate}/location")
	public Iterable<VehicleLocation> retrieveAllVehicleLocations(@PathVariable String vehiclePlate) {

		VehicleLocation vehicleLocation = new VehicleLocation();
		Vehicle vehicle = new Vehicle();
		vehicle.setPlate(vehiclePlate);
		vehicleLocation.setVehicle(vehicle);
		Example<VehicleLocation> vehicleLocationExample = Example.of(vehicleLocation);

		return vehicleLocationRepository.findAll(vehicleLocationExample);
	}

	@GetMapping("/vehicles/{vehiclePlate}/location/count") public Long countVehicleLocations(@PathVariable String vehiclePlate) {

		VehicleLocation vehicleLocation = new VehicleLocation();
		Vehicle vehicle = new Vehicle();
		vehicle.setPlate(vehiclePlate);
		vehicleLocation.setVehicle(vehicle);
		Example<VehicleLocation> vehicleLocationExample = Example.of(vehicleLocation);

		return vehicleLocationRepository.count(vehicleLocationExample);
	}
}
