package com.kinga.microservice.reservation.services;

import com.kinga.microservice.reservation.domain.Vehicule;
import com.kinga.microservice.reservation.domain.Voyage;
import com.kinga.microservice.external.service.modele.Ligne;
import com.kinga.microservice.external.service.modele.Lieu;

import java.util.List;
import java.util.Optional;

public interface VoyageService {
      public Voyage updateOrCreate(Voyage voyage);
      public Voyage initialiseVoyage(Lieu depart, Lieu destination, String username);
      public Voyage initialiseVoyage(Ligne ligne, String username);
      public Optional<Voyage> findById(String id );
      public List<Voyage> listeByLigne(String ligne);
      public List<Voyage> depToDest(String dep, String dest);
      public  Iterable<Voyage>allPassBy(String dep, String dest);
      public Voyage initPlaces(Voyage voyage, Vehicule vehicule) ;
      }
