package com.lincolnpomper.locationapi.location;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository public interface LocationRepository extends CrudRepository<Location, Long> {

}
