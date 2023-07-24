package com.kinga.microservice.reservation.repositories;

import com.kinga.microservice.reservation.domain.Voyage;
import com.kinga.microservice.reservation.domain.Voyageur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *  Created by tokinao on 25/06/23.
 */
public interface VoyageRepository extends MongoRepository<Voyage, String> {
    @Query("{'stringEscales': {$regex: '^?0.*?1$', $options: 'i'}}")
    List<Voyage> getFindAndEnd(String startWith, String endWith);
}
