package com.kinga.microservice.reservation.repositories;

import com.kinga.microservice.reservation.domain.Reserved;
import com.kinga.microservice.reservation.domain.ReservedPlace;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *  Created by tokinao on 25/06/23.
 */
public interface ReservedPlaceRepository extends CrudRepository<ReservedPlace, ObjectId> {
    @Query("{'place': ?0, 'idVoyage': ?1}")
    public List<ReservedPlace> findByPlaceVoyage(String place,String idVoyage);
}
