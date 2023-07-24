package com.kinga.microservice.reservation.services;

import com.kinga.microservice.reservation.domain.Reservation;
import com.kinga.microservice.reservation.domain.Reserved;
import com.kinga.microservice.reservation.domain.ReservedPlace;
import com.kinga.microservice.reservation.repositories.PlaceRepository;
import com.kinga.microservice.reservation.repositories.ReservationRepository;
import com.kinga.microservice.reservation.repositories.ReservedPlaceRepository;
import com.kinga.microservice.reservation.repositories.ReservedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservedPlaceServiceImpl implements ReservedPlaceService {
    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ReservedRepository reservedRepository;
    @Autowired
    ReservedPlaceRepository reservedPlaceRepository;


    public Reservation saveReservation(Reservation reservation) {
        List<Reserved> reserveds = reservation.getReserveds();
        reservation.setReserveds(new ArrayList<>());
        reservation = reservationRepository.save(reservation);
        reserveds = saveReserveds(reserveds, reservation.getId(),reservation.getIdVoyage());
        reservation.setReserveds(reserveds);
        reservation = reservationRepository.save(reservation);
        return reservation;
    }

    public List<Reserved> saveReserveds(List<Reserved> reserveds, String idReservation, String idVoyage) {
        if (CollectionUtils.isEmpty(reserveds)) {
            throw new RuntimeException("reserveds is empty ");
        }

        List<Reserved> reservedsSaved = new ArrayList<>();
        for (Reserved reserved : reserveds) {
            List<ReservedPlace> reservedPlaces = reserved.getReservedPlace();
            reserved.setReservedPlace(new ArrayList<>());
            reserved.setIdVoyage(idVoyage);
            reserved = reservedRepository.save(reserved);
            reservedPlaces = saveReservedPlaces(reservedPlaces, reserved.getId().toHexString(), idReservation, reserved.getIdVoyage());
            reserved.setReservedPlace(reservedPlaces);
            reservedsSaved.add(reserved);
        }
        return reservedsSaved;
    }

    public List<ReservedPlace> saveReservedPlaces(List<ReservedPlace> reservedPlaces, String idReserved, String idReservation, String idVoyage) {
        if (CollectionUtils.isEmpty(reservedPlaces)) {
            throw new RuntimeException("ReservedPlaces is empty ");
        }
        List<ReservedPlace> reservedPlacesSaved = new ArrayList<>();
        for (ReservedPlace reservedPlace : reservedPlaces) {
            reservedPlace.setIdReserved(idReserved);
            reservedPlace.setIdReservation(idReservation);
            reservedPlace.setIdVoyage(idVoyage);
            reservedPlacesSaved.add(reservedPlaceRepository.save(reservedPlace));
        }
        return reservedPlacesSaved;
    }
}