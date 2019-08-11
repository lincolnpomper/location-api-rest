package com.lincolnpomper.locationapi.data;

import com.lincolnpomper.locationapi.location.Location;
import com.lincolnpomper.locationapi.location.LocationRepository;
import com.lincolnpomper.locationapi.vehicle.Vehicle;
import com.lincolnpomper.locationapi.vehicle.VehicleLocation;
import com.lincolnpomper.locationapi.vehicle.VehicleLocationRepository;
import com.lincolnpomper.locationapi.vehicle.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component public class DataLoader {

	private LocationRepository locationRepository;
	private VehicleRepository vehicleRepository;
	private VehicleLocationRepository vehicleLocationRepository;

	@Autowired public DataLoader(LocationRepository locationRepository, VehicleRepository vehicleRepository,
			VehicleLocationRepository vehicleLocationRepository) {
		this.locationRepository = locationRepository;
		this.vehicleRepository = vehicleRepository;
		this.vehicleLocationRepository = vehicleLocationRepository;
	}

	@PostConstruct
	public void setupData() {

		List<LocationFileData> locationOfInterestFileData =
				LocationFileReader.getInstance().getList(LocationFileReader.ResourceType.LocationOfInterest);
		List<LocationFileData> vehiclePositionsFileData =
				LocationFileReader.getInstance().getList(LocationFileReader.ResourceType.VehiclePositions);

		locationOfInterestFileData.forEach(data -> locationRepository.save(new Location(data)));

		vehiclePositionsFileData.forEach(DataLoader::extractRelevantDateTime);
		vehiclePositionsFileData.forEach(data -> {
			final Vehicle vehicle = vehicleRepository.save(new Vehicle(data.name));
			vehicleLocationRepository.save(new VehicleLocation(data, vehicle));
		});
	}

	private static void extractRelevantDateTime(LocationFileData data) {

		final String[] splittedDate = data.values[0].split(" ");
		final String relevantDate =
				splittedDate[1] + " " + splittedDate[2] + " " + splittedDate[3] + " " + splittedDate[4] + " " +
						splittedDate[5];

		data.values[0] = relevantDate;
	}
}