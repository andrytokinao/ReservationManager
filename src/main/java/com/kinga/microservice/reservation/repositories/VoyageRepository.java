package com.kinga.microservice.reservation.repositories;

import com.kinga.microservice.reservation.domain.Voyage;
import com.kinga.microservice.reservation.domain.Voyageur;
import org.springframework.data.repository.CrudRepository;
/**
 *  Created by tokinao on 25/06/23.
 */
public interface VoyageRepository extends CrudRepository<Voyage, String> {
}
