package com.kinga.microservice.reservation.controllers;

import com.kinga.microservice.external.service.modele.Lieu;
import com.kinga.microservice.reservation.VoyageService;
import com.kinga.microservice.reservation.domain.Voyage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoyageController {
    @Autowired
    VoyageService voyageService;
    @RequestMapping({"/voyage/initVoyage"})
    public Voyage initVoyage(Lieu depart, Lieu arrive, String username){
        return voyageService.initialiseVoyage(depart,depart,username);
    }

}
