package com.kinga.microservice.reservation;

import com.kinga.microservice.external.service.DirectionService;
import com.kinga.microservice.external.service.modele.Lieu;
import com.kinga.microservice.reservation.domain.*;
import com.kinga.microservice.reservation.modeles.Place;
import com.kinga.microservice.reservation.modeles.PlaceDispo;
import com.kinga.microservice.reservation.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReservationServiceImple implements ReservationService{
    @Autowired
    VoyageService voyageService ;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    DirectionService directionService ;
    @Override
    public Reservation createOrSave(Reservation reservation) {
        /*TODO Verification de place , et envoyer de remarque
        ** Cas 1 : Evoyer de message erreur si le place est en esce ( autoriser ou non )
        ** Cas 2 : Envoyer des message si quelque doit changer de place dans un escale
        ** TODO : Metre ajoute les voyage des observateur
        * */
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Place> getPlaceDispo(String idVoyage, Lieu depart, Lieu dest) {
        return null;
    }

    @Override
    public List<Place> getPlaceDispo(String idVoyage, String depart, String dest) {
        Optional<Voyage> optionalVoyage = voyageService.findById(idVoyage);
        if(!optionalVoyage.isPresent())
           throw new RuntimeException("Voyage #"+idVoyage+" not found");
        Voyage  voyage = optionalVoyage.get();
        Lieu departLieu = directionService.getLieuById(depart);
        Lieu destLieu = directionService.getLieuById(dest);
        List<Lieu> escales = voyage.getEscales();
        if(CollectionUtils.isEmpty(escales) || !escales.contains(departLieu) || escales.contains(destLieu)){
            throw new RuntimeException("Not on voyage escales ");
        }
        Vehicule vehicule = voyage.getVehicule();
        // Finding the place
        List<Reservation> reservations = voyage.getReservations();
        List<Place> places = vehicule.getTypeVoiture().getAllPlace();
        int nombreDispo = places.size();
        PlaceDispo placeDispo = new PlaceDispo();
        List<Place> placeSemiDispo = new ArrayList<>();
        if(!CollectionUtils.isEmpty(reservations)){
            placeDispo.setDisponible(places);
            placeDispo.setQuasiDispo(placeSemiDispo);
            placeDispo.setNombreDisponible(nombreDispo);
            return places;
        }

        // Récupérer la liste de tous les réservés
        List<Reserved> reserveds = reservations.stream()
                .flatMap(reservation -> reservation.getReserveds().stream())
                .collect(Collectors.toList());

        boolean isBetweenEscales = false;
        nombreDispo = 0 ;
        for(Lieu escale : voyage.getEscales()) {
            // Vérifier si l'escale correspond à la destination de départ
            if (escale.getId().equals(departLieu)) {
                nombreDispo = places.size();
                isBetweenEscales = true;
            }

            // Vérifier si l'escale correspond à la destination de départ
            if (escale.getId().equals(dest)) {
                break;
            }
            int nbNewReserved = 0;
            for (Reserved reserved : reserveds) {
                if (!reserved.getDepart().equals(escale)) {
                    continue;
                }
                places.remove(reserved.getPlace());
                if(isBetweenEscales){
                     nbNewReserved ++;
                }
            }
            // Vérifier les réservations d'arrivée pour l'escale
            for (Reserved reserved : reserveds) {
                if (! reserved.getDestination().equals(escale))
                    continue;
                if (!isBetweenEscales) {
                    places.add(reserved.getPlace());
                    continue;
                }
                if (nbNewReserved > 0) {
                    nbNewReserved ++;

                }

        }
        }
    }

    public List<Lieu> getEscalesVoyage() {
        List<Reserved> reservedList = new ArrayList<>();

    }

}
