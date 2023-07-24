package com.kinga.microservice.reservation.repositories;

import com.kinga.microservice.reservation.domain.Action;
import com.kinga.microservice.reservation.domain.Place;
import org.springframework.data.repository.CrudRepository;

/**
 *  Created by tokinao on 25/06/23.
 */
public interface PlaceRepository extends CrudRepository<Place, String> {

}
