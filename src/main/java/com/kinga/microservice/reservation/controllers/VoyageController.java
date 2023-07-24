package com.kinga.microservice.reservation.controllers;

import com.kinga.microservice.external.service.modele.Lieu;
import com.kinga.microservice.reservation.services.VoyageService;
import com.kinga.microservice.reservation.domain.Vehicule;
import com.kinga.microservice.reservation.domain.Voyage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VoyageController {
    @Autowired
    VoyageService voyageService;
    @RequestMapping({"/voyage/initVoyage"})
    public Voyage initVoyage(Lieu depart, Lieu arrive, String username){
        return voyageService.initialiseVoyage(depart,depart,username);
    }
    public List<String> getLignes(String user){
        return null;
    }
    public List<Lieu> getEscales(Lieu dep, Lieu dest){
        return null;
    }
    public List<String> getAllResponsables(String idCompany, String user, String ligne){
        return null;
    }
    public  List<Vehicule> getAllVoiture(String idCompany, String user, String ligne){
        return null;
    }

}
