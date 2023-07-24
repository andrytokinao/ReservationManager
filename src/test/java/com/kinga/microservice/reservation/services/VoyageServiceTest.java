package com.kinga.microservice.reservation.services;

import com.kinga.microservice.external.service.CompanyService;
import com.kinga.microservice.external.service.RouteService;
import com.kinga.microservice.external.service.modele.Company;
import com.kinga.microservice.external.service.modele.Lieu;
import com.kinga.microservice.external.service.modele.Ligne;
import com.kinga.microservice.reservation.domain.*;
import com.kinga.microservice.reservation.domain.Place;
import com.kinga.microservice.reservation.modeles.ReservedStatus;
import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VoyageServiceTest  extends TestCase {
    @Autowired
    ReservationService reservationService;
    @Autowired
    CompanyService companyService;
    @Autowired
    RouteService routeService;
    @Autowired
    VoyageService voyageService;
    @Test
    public void createVoyageTest(){
        // Etape 1 recuperation de company
        Company company = companyService.getCompanyByUsername("username");
        assertNotNull(company);
        // Recuperation des ligne pour une company
        List<Ligne> lignes = companyService.ligneDispoCompany(company.getId());
        assertTrue(!CollectionUtils.isEmpty(lignes));
        // Choix de la ligne
        Ligne l = lignes.get(0);
        Voyage voyage = voyageService.initialiseVoyage(l,"username");
        List<Lieu> escales = voyage.getEscales();
        assertTrue(!CollectionUtils.isEmpty(escales));

        // Get vehicule by company > initialiser les place
        List<Vehicule> vehicules = companyService.getVehicules(company.getId());
        Vehicule vehicule = vehicules.get(0);
        voyage = voyageService.initPlaces(voyage,vehicule);
        assertTrue(!CollectionUtils.isEmpty(voyage.getPlaces()));


    // Choie voiture / Chauffeur
        List<Personne> chauffeurs = companyService.getChauffeurs(company.getId());
        assertTrue(!CollectionUtils.isEmpty(chauffeurs));
        Personne chauffeur = chauffeurs.get(0);
        List<Personne> newChauffeur = new ArrayList<>();
        newChauffeur.add(chauffeur);
        voyage.setChauffeurs(newChauffeur);


        // Get responsable et selection
        List<Personne> responsables = companyService.getResponsablesByCompany(company.getId());
        voyage.setResponsables(responsables);
        assertNotNull(responsables);
        voyage = voyageService.updateOrCreate(voyage);
        assertNotNull(voyage.getId());


        // Essaiyer de trouver les place disponible
        Collection<Place> placeDispos = voyage.getPlaces();
        assertTrue(!CollectionUtils.isEmpty(placeDispos));
        
        // Essaiyer de reserver une place 1-1 , 1-2 , 1-3 , 1-4
        Reservation reservation = new Reservation();
        reservation.setIdVoyage(voyage.getId());
        reservation.setUtilisateur("user-for-test");
        List<Reserved> reserveds = new ArrayList<>();

        for (int j = 1; j <= 4; j++) {
            Place place = new Place(1, j);
            Lieu depart = escales.get(0);
            Lieu dest = escales.get(escales.size() - 1);
            Voyageur reserved = new Voyageur();
            reserved.setContact("CONTACT");
            reserved.setDepart(depart);
            reserved.setDestination(dest);
            reserved.setFirstName("FirstName-" + j);
            reserved.setLastName("LastName-" +  j);
            reserved.setContact("CONTACT");
            reserveds.add(reserved);
            ReservedPlace reservedPlace = new ReservedPlace();
            reservedPlace.setPlace(place.toString());
            reservedPlace.setIndexDepart(0);
            reservedPlace.setIndexDestination(escales.size() - 1);
            List<ReservedPlace> reservedPlaces = new ArrayList<>();
            reservedPlaces.add(reservedPlace);
            reserved.setReservedPlace(reservedPlaces);
        }
        reservation.setReserveds(reserveds);
        List<ReservedStatus> results = reservationService.createOrSave(reservation, voyage.getId());
        // tous les resultat sont success
        for(ReservedStatus statut : results){
            assertTrue(StringUtils.equalsIgnoreCase(statut.getStatus(),ReservedStatus.STATUS_SUCCESS));
        }



        // Essaiyer de reserver une place 1-1 , 1-2 , 1-3 , 1-4
        Reservation reservation2 = new Reservation();
        reservation.setIdVoyage(voyage.getId());
        reservation.setUtilisateur("user-for-test");
        List<Reserved> reserveds2 = new ArrayList<>();

        for (int j = 1; j <= 4; j++) {
            Place place = new Place(1, j);
            Lieu depart = escales.get(0);
            Lieu dest = escales.get(escales.size() - 1);
            Voyageur reserved = new Voyageur();
            reserved.setContact("CONTACT");
            reserved.setDepart(depart);
            reserved.setDestination(dest);
            reserved.setFirstName("FirstName-" + j);
            reserved.setLastName("LastName-" +  j);
            reserved.setContact("CONTACT");
            reserveds2.add(reserved);
            ReservedPlace reservedPlace = new ReservedPlace();
            reservedPlace.setPlace(place.toString());
            reservedPlace.setIndexDepart(0);
            reservedPlace.setIndexDestination(escales.size() - 1);
            List<ReservedPlace> reservedPlaces = new ArrayList<>();
            reservedPlaces.add(reservedPlace);
            reserved.setReservedPlace(reservedPlaces);
        }
        reservation.setReserveds(reserveds2);
        List<ReservedStatus> notDispo = reservationService.createOrSave(reservation, voyage.getId());
        // tous les resultat sont non disponible 
        for(ReservedStatus statut : notDispo){
            assertTrue(StringUtils.equalsIgnoreCase(statut.getStatus(),ReservedStatus.STATUS_NOT_DISPONIBLE));
        }
        // Recuperer l'etat de places en cours 
        List<Place> places = reservationService.statesPlaces(voyage.getId(), escales.get(0).getId(), escales.get(escales.size() - 1).getId());
        Optional<Place> placeNotDispo = places.stream().filter(place -> StringUtils.equalsIgnoreCase(place.getEtat(),ReservedStatus.STATUS_NOT_DISPONIBLE)).findFirst();
        assertTrue(placeNotDispo.isPresent());


        // Tester le quasi-Reserved



        // Deserved en demiroute 2-1 , 2-2 , 2-3 , 2-4
        Reservation reservation3 = new Reservation();
        reservation.setIdVoyage(voyage.getId());
        reservation.setUtilisateur("user-for-test");
        List<Reserved> reserveds3 = new ArrayList<>();

        for (int j = 1; j <= 4; j++) {
            Place place = new Place(2, j);
            Lieu depart = escales.get(0);
            Lieu dest = escales.get(escales.size() - 1);
            Voyageur reserved = new Voyageur();
            reserved.setContact("CONTACT");
            reserved.setDepart(depart);
            reserved.setDestination(dest);
            reserved.setFirstName("FirstName-" + j);
            reserved.setLastName("LastName-" +  j);
            reserved.setContact("CONTACT");
            reserveds3.add(reserved);
            ReservedPlace reservedPlace = new ReservedPlace();
            reservedPlace.setPlace(place.toString());
            reservedPlace.setIndexDepart(j);
            reservedPlace.setIndexDestination(escales.size() - 5 +j);
            List<ReservedPlace> reservedPlaces = new ArrayList<>();
            reservedPlaces.add(reservedPlace);
            reserved.setReservedPlace(reservedPlaces);
        }
        reservation3.setReserveds(reserveds3);
        List<ReservedStatus> reservedSuccess = reservationService.createOrSave(reservation3, voyage.getId());
// tous les resultat sont success
        for(ReservedStatus statut : reservedSuccess){
            assertTrue(StringUtils.equalsIgnoreCase(statut.getStatus(),ReservedStatus.STATUS_SUCCESS));
        }
        // Recuperer l'etat de places quasi-reserved
        List<Place> places3 = reservationService.statesPlaces(voyage.getId(), escales.get(0).getId(), escales.get(escales.size() - 1).getId());
        Optional<Place> placeSemiDispo = places3.stream().filter(place -> StringUtils.equalsIgnoreCase(place.getEtat(),ReservedStatus.STATUS_QUASI_DISPONIBLE)).findFirst();
        assertTrue(placeSemiDispo.isPresent());
    }
    @Test
    public void create(){
        // Etape 1 recuperation de company
        Company company = companyService.getCompanyByUsername("username");
        assertNotNull(company);
        // Recuperation des ligne pour une company
        List<Ligne> lignes = companyService.ligneDispoCompany(company.getId());
        assertTrue(!CollectionUtils.isEmpty(lignes));
        // Choix de la ligne
        Ligne l = lignes.get(0);
        Voyage voyage = voyageService.initialiseVoyage(l,"username");
        List<Lieu> escales = voyage.getEscales();
        assertTrue(!CollectionUtils.isEmpty(escales));

        // Get vehicule by company > initialiser les place
        List<Vehicule> vehicules = companyService.getVehicules(company.getId());
        Vehicule vehicule = vehicules.get(0);
        voyage = voyageService.initPlaces(voyage,vehicule);
        assertTrue(!CollectionUtils.isEmpty(voyage.getPlaces()));


        // Choie voiture / Chauffeur
        List<Personne> chauffeurs = companyService.getChauffeurs(company.getId());
        assertTrue(!CollectionUtils.isEmpty(chauffeurs));
        Personne chauffeur = chauffeurs.get(0);
        List<Personne> newChauffeur = new ArrayList<>();
        newChauffeur.add(chauffeur);
        voyage.setChauffeurs(newChauffeur);

        // Get responsable et selection
        List<Personne> responsables = companyService.getResponsablesByCompany(company.getId());
        voyage.setResponsables(responsables);
        assertNotNull(responsables);
        voyage = voyageService.updateOrCreate(voyage);
        assertNotNull(voyage.getId());
    }
    @Test
    public void testShearch(){
        for(int i = 0 ; i < 5 ; i++){
            create();
        }
        List<Voyage> res5 = voyageService.depToDest("1", "-10");
        assertTrue(res5.size() == 5);
    }

}
