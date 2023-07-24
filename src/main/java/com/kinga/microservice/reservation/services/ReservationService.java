package com.kinga.microservice.reservation.services;

import com.kinga.microservice.external.service.modele.Lieu;
import com.kinga.microservice.reservation.domain.Reservation;
import com.kinga.microservice.reservation.domain.TypeVoiture;
import com.kinga.microservice.reservation.domain.Place;
import com.kinga.microservice.reservation.modeles.ReservedStatus;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ReservationService {
    public List<ReservedStatus> createOrSave(Reservation reservation, String idVoyage);
    public List<Place> statesPlaces(String idVoyage, String begen, String end);
    public Integer nombrePlaceDispo(String idVoyage, String begen, String end);

}
