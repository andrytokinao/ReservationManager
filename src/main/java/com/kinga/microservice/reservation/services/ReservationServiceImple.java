package com.kinga.microservice.reservation.services;

import com.kinga.microservice.external.service.RouteService;
import com.kinga.microservice.external.service.modele.Lieu;
import com.kinga.microservice.reservation.domain.*;
import com.kinga.microservice.reservation.domain.Place;
import com.kinga.microservice.reservation.modeles.ReservedStatus;
import com.kinga.microservice.reservation.repositories.ReservationRepository;
import com.kinga.microservice.reservation.repositories.ReservedPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImple implements ReservationService {
    @Autowired
    VoyageService voyageService;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ReservedPlaceRepository reservedPlaceRepository;
    @Autowired
    RouteService routeService;
    @Autowired
    ReservedPlaceService reservedPlaceService;

    @Override
    public List<ReservedStatus> createOrSave(Reservation reservation, String idVoyage) {
        /**
         * Verification sur le payement
         **/
        if (reservation == null || CollectionUtils.isEmpty(reservation.getReserveds())) {
            throw new RuntimeException("Reservation invalid");
        }
        for (Reserved reserved : reservation.getReserveds()) {
            if (CollectionUtils.isEmpty(reserved.getReservedPlace())) {
                throw new RuntimeException("Reservation invalid ; Place unspecified ");
            }
        }
        List<ReservedPlace> reservedPlaces = reservation.getReserveds().stream()
                .flatMap(reserved -> (reserved.getReservedPlace().stream()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(reservedPlaces)) {
            throw new RuntimeException("Reservation invalid");
        }
        Map<String, List<ReservedPlace>> places = new HashMap<>();
        for (ReservedPlace rp : reservedPlaces) {
            String p = rp.getPlace();
            List<ReservedPlace> reserPs = places.get(p);
            if (CollectionUtils.isEmpty(reserPs)) {
                reserPs = new ArrayList<>();
                reserPs.add(rp);
                places.put(p, reserPs);
            } else {
                reserPs.add(rp);
                places.replace(p, reserPs);
            }

        }


        Voyage voyage = voyageService.findById(idVoyage).get();
        if (voyage == null) {
            throw new RuntimeException("Voyage " + idVoyage + " not found");
        }

        if (CollectionUtils.isEmpty(voyage.getPlaces()))
            throw new RuntimeException("Voyage " + idVoyage + " has not places");
        if (CollectionUtils.isEmpty(voyage.getEscales()))
            throw new RuntimeException("Voyage " + idVoyage + " Has not escales ");

        List<ReservedStatus> reservedStatuses = new ArrayList<>();
        List<ReservedPlace> reservedPlacesToSave = new ArrayList<>();
        for (String p : places.keySet()) {
            Optional<Place> optionalPlace = voyage.getPlaces().stream().filter(pl -> p.equalsIgnoreCase(pl.toString())).findFirst();
            if (!optionalPlace.isPresent()) {
                throw new RuntimeException("Voyage " + idVoyage + " Place " + p.toString() + " is not presente ");
            }
            Place place = optionalPlace.get();
            List<ReservedPlace> listeReserveds = places.get(p);


            List<ReservedPlace> existingToVerifie = CollectionUtils.isEmpty(reservedPlaceRepository.findByPlaceVoyage(p.toString(), idVoyage)) ? new ArrayList<>() : reservedPlaceRepository.findByPlaceVoyage(p.toString(), idVoyage);
            ;
            List<ReservedPlace> toSave = new ArrayList<>();
            for (ReservedPlace rp : listeReserveds) {
                reservedStatuses.add(verifiePlace(voyage, rp, existingToVerifie));
                toSave.add(rp);
                existingToVerifie.add(rp);
            }
            reservedPlacesToSave.addAll(toSave);
        }
        List<ReservedStatus> notDisponible = reservedStatuses.stream().filter(rs -> !ReservedStatus.STATUS_DISPONIBLE.equals(rs.getStatus())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(notDisponible))
            return reservedStatuses;
        try {
            reservation.setIdVoyage(idVoyage);
            reservation = reservedPlaceService.saveReservation(reservation);
            for (ReservedStatus rstatus : reservedStatuses)
                rstatus.setStatus(ReservedStatus.STATUS_SUCCESS);
        } catch (Exception ex) {
            reservedStatuses.add(new ReservedStatus(ReservedStatus.STATUS_ERROR, ex.getMessage()));
        }
        return reservedStatuses;

    }

    @Override
    public List<Place> statesPlaces(String idVoyage, String begen, String end) {
        List<Place> places = new ArrayList<>();
        Voyage voyage = voyageService.findById(idVoyage).get();
        if (voyage == null) {
            throw new RuntimeException("Voyage " + idVoyage + " not found");
        }
        Integer indexBegen = null;
        Integer indexEnd = null;
        int i = 0;
        List<Lieu> escales = voyage.getEscales();
        for (Lieu l : escales) {
            if (StringUtils.endsWithIgnoreCase(l.getId(), begen))
                indexBegen = i;
            if (StringUtils.endsWithIgnoreCase(l.getId(), end))
                indexEnd = i;
            i++;
        }
        if (indexBegen == null || indexEnd == null || indexEnd <= indexBegen)
            throw new RuntimeException("Voyage " + idVoyage + " Escals " + begen + " to " + end + " is not valide");


        if (CollectionUtils.isEmpty(voyage.getPlaces()))
            throw new RuntimeException("Voyage " + idVoyage + " has not places");
        for (Place p : voyage.getPlaces()) {
            List<ReservedPlace> existingToVerifie = CollectionUtils.isEmpty(reservedPlaceRepository.findByPlaceVoyage(p.toString(), idVoyage)) ? new ArrayList<>() : reservedPlaceRepository.findByPlaceVoyage(p.toString(), idVoyage);
            ;
            List<ReservedPlace> toSave = new ArrayList<>();
            ReservedPlace rp = new ReservedPlace();
            rp.setIndexDepart(indexBegen);
            rp.setIndexDestination(indexEnd);
            p.setEtat(verifiePlace(voyage, rp, existingToVerifie).getStatus());
            places.add(p);
        }
        return places;
    }

    @Override
    public Integer nombrePlaceDispo(String idVoyage, String begen, String end) {
        return null;
    }

    private ReservedStatus verifiePlace(Voyage voyag, ReservedPlace reservedPlace, List<ReservedPlace> listeExisting) {
        if (reservedPlace.getIndexDestination() > voyag.getEscales().size() || reservedPlace.getIndexDepart() < 0)
            return new ReservedStatus(ReservedStatus.STATUS_ERROR, "Not correct");
        if (CollectionUtils.isEmpty(listeExisting)) {
            return new ReservedStatus(ReservedStatus.STATUS_DISPONIBLE, " Place is disponible  ");
        }

        List<String> ligneNotDispo = new ArrayList<>();
        for(ReservedPlace rpv : listeExisting){
            int begen = rpv.getIndexDepart();
            int end = rpv.getIndexDestination();
            for(int i = begen ; i < end ; i++  ){
                ligneNotDispo.add(i+"-"+(i+1));
            }
        }
        int depart = reservedPlace.getIndexDepart();
        int dest = reservedPlace.getIndexDestination();
        boolean quasi = false ;
        boolean noDispo = false ;
        for(int i = depart; i<dest ; i++){
            if(ligneNotDispo.contains(i+"-"+(i+1))){
                noDispo = true;
            }else {
                quasi = true;
            }
        }

        if ((quasi) && (!noDispo) ) {
            return new ReservedStatus(ReservedStatus.STATUS_DISPONIBLE, " Place  is disponible  ");
        }
        if ((quasi) && (noDispo)) {
            return new ReservedStatus(ReservedStatus.STATUS_QUASI_DISPONIBLE, " Place  quasi disponible ");
        }
        if ((!quasi) && (noDispo)) {
            return new ReservedStatus(ReservedStatus.STATUS_NOT_DISPONIBLE, " Place  quasi disponible ");
        }
        return new ReservedStatus(ReservedStatus.STATUS_DISPONIBLE, " Place  is disponible  ");

    }
}
