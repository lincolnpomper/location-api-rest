package com.lincolnpomper.locationapi.vehicle;

import com.lincolnpomper.locationapi.coordinate.Coordinate;
import com.lincolnpomper.locationapi.coordinate.FindDistance;
import com.lincolnpomper.locationapi.coordinate.Unit;
import com.lincolnpomper.locationapi.location.Location;
import com.lincolnpomper.locationapi.location.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service("vehicleLocationService")
public class VehicleLocationServiceImpl implements VehicleLocationService {

	@Autowired private VehicleLocationRepository vehicleLocationRepository;
	@Autowired private LocationRepository locationRepository;

	@Override public SortedSet<VehicleLocationGrouped> getLocationsByPlateAndDateGroupedByPoi(String vehiclePlate, LocalDateTime startDate,
			LocalDateTime endDate) {

		Vehicle vehicle = new Vehicle(vehiclePlate);
		List<VehicleLocation> listVehicleLocation = vehicleLocationRepository.findAllByVehicle(vehicle.getPlate());
		listVehicleLocation = filterByDate(startDate, endDate, listVehicleLocation);

		final SortedSet<VehicleLocationGrouped> result = new TreeSet<>(Comparator.comparing(grouped -> grouped.location.getName()));

		for (Location location : locationRepository.findAll()) {

			final List<VehicleLocation> vehicleLocationInsideRadius = listVehicleLocation.stream()
					.filter(vehicleLocation -> isInsideRadius(new Coordinate(location), vehicleLocation, location.getRadius()))
					.collect(Collectors.toList());

			final SortedSet<VehicleLocation> sortedLocations = new TreeSet<>(Comparator.comparing(VehicleLocation::getDate));
			sortedLocations.addAll(vehicleLocationInsideRadius);

			result.add(new VehicleLocationGrouped(location, sortedLocations));
		}

		return result;
	}

	private List<VehicleLocation> filterByDate(LocalDateTime startDate, LocalDateTime endDate, List<VehicleLocation> listVehicleLocation) {

		return listVehicleLocation.stream()
				.filter(item -> item.getDate().isAfter(startDate) && item.getDate().isBefore(endDate))
				.collect(Collectors.toList());
	}

	@Override public List<VehicleLocationTimeVO> getLocationsByPlateAndDateWithSpentTime(String vehiclePlate, LocalDateTime startDate,
			LocalDateTime endDate) {

		SortedSet<VehicleLocationGrouped> set = getLocationsByPlateAndDateGroupedByPoi(vehiclePlate, startDate, endDate);

		List<VehicleLocationTimeVO> list = new ArrayList<>();

		set.forEach(item -> {

			if (!item.vehicleLocationList.isEmpty()) {

				final Vehicle vehicle = item.vehicleLocationList.first().getVehicle();

				Optional<VehicleLocation> optionalMin =
						item.vehicleLocationList.stream().min(Comparator.comparing(VehicleLocation::getDate));
				Optional<VehicleLocation> optionalMax =
						item.vehicleLocationList.stream().max(Comparator.comparing(VehicleLocation::getDate));

				if (optionalMin.isPresent() && optionalMax.isPresent()) {
					LocalDateTime vehicleStartDate = optionalMin.get().getDate();
					LocalDateTime vehicleEndDate = optionalMax.get().getDate();
					long minutes = Duration.between(vehicleStartDate, vehicleEndDate).getSeconds() / 60;
					list.add(new VehicleLocationTimeVO(vehicle, item.location, minutes));
				}
			}
		});

		return list;
	}

	private static boolean isInsideRadius(Coordinate POI, VehicleLocation vehicle, int radius) {
		return FindDistance.distanceBetween(POI, new Coordinate(vehicle.getLatitude(), vehicle.getLongitude()), Unit.M) <= radius;
	}

}
