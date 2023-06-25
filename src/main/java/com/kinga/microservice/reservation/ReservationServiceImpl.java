package com.kinga.microservice.reservation;

import com.kinga.microservice.reservation.commands.VoyageurForm;
import com.kinga.microservice.reservation.converters.ToVoyageur;
import com.kinga.microservice.reservation.domain.Voyageur;
import com.kinga.microservice.reservation.repositories.VoyageurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
@Service
public class ReservationServiceImpl implements ReservationService {

    private VoyageurRepository voyageurRepository;
    private ToVoyageur toVoyageur;

    @Autowired
    public ReservationServiceImpl(VoyageurRepository voyageurRepository, ToVoyageur toVoyageur) {
        this.voyageurRepository = voyageurRepository;
        this.toVoyageur = toVoyageur;
    }


    @Override
    public List<Voyageur> listAll() {
        List<Voyageur> voyageurs = new ArrayList<>();
        voyageurRepository.findAll().forEach(voyageurs::add); //fun with Java 8
        return voyageurs;
    }

    @Override
    public Voyageur getById(String id) {
        return voyageurRepository.findById(id).orElse(null);
    }

    @Override
    public Voyageur saveOrUpdate(Voyageur voyageur) {
        voyageurRepository.save(voyageur);
        return voyageur;
    }

    @Override
    public void delete(String id) {
        voyageurRepository.deleteById(id);
    }

    @Override
    public Voyageur saveOrUpdatevoyageurForm(VoyageurForm voyageurForm) {
        Voyageur savedVoyageur = saveOrUpdate(toVoyageur.convert(voyageurForm));

        System.out.println("Saved Voyageur Id: " + savedVoyageur.getId());
        return savedVoyageur;
    }
}
