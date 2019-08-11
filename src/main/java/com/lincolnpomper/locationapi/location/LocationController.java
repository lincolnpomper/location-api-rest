package com.lincolnpomper.locationapi.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController public class LocationController {

	@Autowired private LocationRepository locationRepository;

	@GetMapping("/locations") public Iterable<Location> retrieveAllLocations() {
		return locationRepository.findAll();
	}

}
