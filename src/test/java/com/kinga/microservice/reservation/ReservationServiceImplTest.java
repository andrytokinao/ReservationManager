package com.kinga.microservice.reservation;

import com.kinga.microservice.reservation.domain.Reservation;
import com.kinga.microservice.reservation.domain.Reserved;
import com.kinga.microservice.reservation.domain.Voyageur;
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
public class ReservationServiceImplTest extends TestCase {
    @Autowired
    ReservationServiceImpl reservationService;
    @Autowired
    ReservationRepository reservationRepository;
    @Test
    public void testUpdateOrcreateReservation() {
        List<Reserved> reserveds = new ArrayList<>();
        Reservation reservation = new Reservation();
        reservation.setCodeValidation("22222");
        reservation.setNumero("RE-2521");
        Voyageur v1 = new Voyageur();
        for(int i = 0 ; i< 10 ; i++){
            v1.setIdClient("Cient "+i);
            v1.setContact("Contact 1");
            v1.setDepart(200);
            v1.setDestination(33);
            v1.setFirstName("First name "+i);
            v1.setLastName("Last name "+i);
            v1.setReservation(reservation);
            reserveds.add(v1);
        }
       Reservation reservationSaved = reservationService.updateOrcreateReservation(reservation);
        assertNotNull(reservationSaved);
        assertNotNull(reservationSaved.getId());
        assertEquals(reservationRepository.count(),1);

        reservation = new Reservation();
        reservation.setCodeValidation("256");
        reservation.setNumero("RE-25221");
        reservation.setReserveds(reserveds);
        reservationService.updateOrcreateReservation(reservation);
        assertEquals(reservationRepository.count(),2);
    }
}