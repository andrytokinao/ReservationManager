package com.kinga.microservice.reservation.repositories;

import com.kinga.microservice.reservation.domain.Reserved;
import com.kinga.microservice.reservation.domain.Voyageur;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jt on 1/10/17.
 */
public interface ReservedRepository extends CrudRepository<Reserved, String> {
}
