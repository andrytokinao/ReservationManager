package com.kinga.microservice.reservation.services;

import com.kinga.microservice.reservation.domain.Reservation;
import com.kinga.microservice.reservation.domain.Reserved;
import com.kinga.microservice.reservation.domain.Voyageur;
import com.kinga.microservice.external.service.modele.Lieu;
import com.kinga.microservice.reservation.repositories.ReservationRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservedPlaceServiceImplTest extends TestCase {
    @Autowired
    ReservedPlaceServiceImpl reservedPlaceService;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ReservationService reservationService;
    @Test
    public void testUpdateOrcreateReservation() {
        List<Reserved> reserveds = new ArrayList<>();
        Reservation reservation = new Reservation();
        reservation.setCodeValidation("22222");
        reservation.setNumero("RE-2521");
        Voyageur v1 = new Voyageur();
        Lieu depart = new Lieu("01","Antananarivo");
        Lieu arrive = new Lieu("02","Antananarivo");
        for(int i = 0 ; i< 10 ; i++){
            v1.setIdClient("Cient "+i);
            v1.setContact("Contact 1");
            v1.setDepart(new Lieu());
            v1.setDestination(new Lieu());
            v1.setFirstName("First name "+i);
            v1.setLastName("Last name "+i);
            v1.setDepart(depart);
            v1.setDestination(arrive);
            reserveds.add(v1);
        }

    }

}