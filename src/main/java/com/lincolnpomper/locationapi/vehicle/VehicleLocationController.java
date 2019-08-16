package com.lincolnpomper.locationapi.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class VehicleLocationController {

	@Autowired private VehicleLocationService vehicleLocationService;

	public VehicleLocationController() {
	}

	@GetMapping("/vehicles/{vehiclePlate}/locations/{stringStartDate}/{stringEndDate}") public List<VehicleLocationTimeVO> getLocationsByPlateAndDateWithSpentTime(
			@PathVariable
					String vehiclePlate,
			@PathVariable
					String stringStartDate,
			@PathVariable
					String stringEndDate) {

		LocalDateTime startDate = convertDateFromString(stringStartDate);
		LocalDateTime endDate = convertDateFromString(stringEndDate);

		return vehicleLocationService.getLocationsByPlateAndDateWithSpentTime(vehiclePlate, startDate, endDate);
	}

	private static LocalDateTime convertDateFromString(String stringDate) {

		String[] stringArray = stringDate.split("-");

		int dayOfMonth = Integer.valueOf(stringArray[0]);
		int month = Integer.valueOf(stringArray[1]);
		int year = Integer.valueOf(stringArray[2]);

		return LocalDateTime.of(year, month, dayOfMonth, 0, 0);
	}
}