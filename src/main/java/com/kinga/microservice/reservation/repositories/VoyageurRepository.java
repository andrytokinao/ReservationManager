package com.kinga.microservice.reservation.repositories;

import com.kinga.microservice.reservation.domain.Voyageur;
import org.springframework.data.repository.CrudRepository;/**
 * Created by tokinao on 25/06/23.
 */
public interface VoyageurRepository extends CrudRepository<Voyageur, String> {
}
