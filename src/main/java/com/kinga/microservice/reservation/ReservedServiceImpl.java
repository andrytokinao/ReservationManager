package com.kinga.microservice.reservation;

import com.kinga.microservice.reservation.domain.Reservation;
import com.kinga.microservice.reservation.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservedServiceImpl implements ReservedService {
   @Autowired
    ReservationRepository reservationRepository ;
    @Override
    public Reservation updateOrcreateReservation(Reservation reservation) {
        Reservation reservation1 = reservation;
       return reservationRepository.save(reservation);
    }
}
