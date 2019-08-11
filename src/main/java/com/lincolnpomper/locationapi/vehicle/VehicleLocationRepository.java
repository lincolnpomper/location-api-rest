package com.lincolnpomper.locationapi.vehicle;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository public interface VehicleLocationRepository extends CrudRepository<VehicleLocation, Long> , QueryByExampleExecutor<VehicleLocation> {

//	List<VehicleLocation> findAllByVehicle(@Param("vehicle") Vehicle vehicle);
}
