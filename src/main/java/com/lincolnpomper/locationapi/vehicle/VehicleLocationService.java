package com.lincolnpomper.locationapi.vehicle;

import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedSet;

public interface VehicleLocationService {

	SortedSet<VehicleLocationGrouped> getLocationsByPlateAndDateGroupedByPoi(String vehiclePlate, LocalDateTime startDate,
			LocalDateTime endDate);

	List<VehicleLocationTimeVO> getLocationsByPlateAndDateWithSpentTime(String vehiclePlate, LocalDateTime startDate,
			LocalDateTime endDate);
}
