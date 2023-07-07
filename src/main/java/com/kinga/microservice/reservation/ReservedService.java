package com.kinga.microservice.reservation;

import com.kinga.microservice.reservation.domain.Reservation;

public interface ReservedService {
    public Reservation updateOrcreateReservation(Reservation reservation);
}
