package com.kinga.microservice.reservation.repositories;

import com.kinga.microservice.reservation.domain.Reservation;
import com.kinga.microservice.reservation.domain.Voyageur;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jt on 1/10/17.
 */
public interface ReservationRepository extends CrudRepository<Reservation, String> {
}
