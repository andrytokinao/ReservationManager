package com.kinga.microservice.reservation;



import com.kinga.microservice.reservation.commands.VoyageurForm;
import com.kinga.microservice.reservation.domain.Voyageur;

import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
public interface ReservationService {


    List<Voyageur> listAll();

    Voyageur getById(String id);

    Voyageur saveOrUpdate(Voyageur voyageur);

    void delete(String id);

    Voyageur saveOrUpdatevoyageurForm(VoyageurForm voyageurForm);
}
