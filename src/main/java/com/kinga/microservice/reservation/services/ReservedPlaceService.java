package com.kinga.microservice.reservation.services;

import com.kinga.microservice.reservation.domain.Reservation;
import com.kinga.microservice.reservation.domain.Reserved;
import com.kinga.microservice.reservation.domain.ReservedPlace;

import java.util.List;

public interface ReservedPlaceService {
    public Reservation saveReservation(Reservation reservation) ;

    public List<Reserved> saveReserveds(List<Reserved> reserveds , String idReservation, String idVoyage) ;


    public List<ReservedPlace> saveReservedPlaces(List<ReservedPlace> reservedPlaces,  String idReserved , String idReservation,  String idVoyage);

    }
