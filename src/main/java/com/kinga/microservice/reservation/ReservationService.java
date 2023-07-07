package com.kinga.microservice.reservation;

import com.kinga.microservice.external.service.modele.Lieu;
import com.kinga.microservice.reservation.domain.Reservation;
import com.kinga.microservice.reservation.modeles.Place;

import java.util.List;

public interface ReservationService {
    public Reservation createOrSave(Reservation reservation);
    public List<Place> getPlaceDispo(String idVoyage,Lieu depart, Lieu dest );
    public Integer getNombrePlaceDispo(String idVoyage, Lieu depart, Lieu dest);
}
