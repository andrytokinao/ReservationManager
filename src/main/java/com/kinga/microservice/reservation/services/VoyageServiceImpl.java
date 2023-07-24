package com.kinga.microservice.reservation.services;

import com.kinga.microservice.external.service.CompanyService;
import com.kinga.microservice.external.service.RouteService;
import com.kinga.microservice.external.service.modele.Company;
import com.kinga.microservice.external.service.modele.Cooperative;
import com.kinga.microservice.reservation.domain.TypeVoiture;
import com.kinga.microservice.reservation.domain.Vehicule;
import com.kinga.microservice.reservation.domain.Voyage;
import com.kinga.microservice.external.service.modele.Ligne;
import com.kinga.microservice.external.service.modele.Lieu;
import com.kinga.microservice.reservation.domain.Place;
import com.kinga.microservice.reservation.repositories.PlaceRepository;
import com.kinga.microservice.reservation.repositories.VoyageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VoyageServiceImpl implements VoyageService{
    @Autowired
    VoyageRepository voyageRepository;
    @Autowired
    CompanyService companyService;
    @Autowired
    RouteService routeService;
    @Autowired
    PlaceRepository placeRepository;
    @Override
    public Voyage updateOrCreate(Voyage voyage) {
        // TODO Verification des entreprise , des donnée null , etc , utilisateur
        String numero = voyage.getNumero();
        if(StringUtils.isBlank(numero)){
            voyage.setNumero("VO-"+(voyageRepository.count()+1));
        }
        return voyageRepository.save(voyage);
    }

    @Override
    public Voyage initialiseVoyage(Lieu depart, Lieu destination, String username) {
        // TODO Recuperation des lieus disponible
        // TODO Recuperation de la company de transport , seulement pour le proprietaire de company ou cooperative
        Voyage voyage = new Voyage();
        Company company = companyService.getCompanyByUsername(username);
        Cooperative cooperative = null;
        if(company == null) {
            cooperative = companyService.getCooperativeByUsername(username);
            if((company == null) && (cooperative == null)){
                throw new RuntimeException("The user "+username +"Can not create voyage ");
            }
        }
        String companyOrCooperative = company != null ? "Company "+company.getName() : "Cooperative "+cooperative.getName();
        Ligne ligne = routeService.getLigne(depart, destination);
        List<Ligne> ligneDispo = company != null ? companyService.ligneDispoCompany(company.getId()) : companyService.ligneDispoCooperative(cooperative.getId());

        if(!ligneDispo.contains(ligne)){
            throw new RuntimeException("Ligne not accessible for "+companyOrCooperative);
        }
        voyage.setLigne(ligne);
        List<Lieu> escales = (routeService.getEscales(depart,destination));
        voyage.setEscales(escales);
        String escaleString = "";
        for(Lieu p : escales) {
            escaleString+=StringUtils.isBlank(escaleString)?p.getId():"-" +p.getId();
        }
        voyage.setStringEscales(escaleString);
        voyage.setFresTransports(routeService.getFres(ligne.getId(),"default"));
        return voyage;
    }

    @Override
    public Voyage initialiseVoyage(Ligne ligne, String username) {
        Ligne ligne1 = routeService.getLigneById(ligne.getId());
        if(ligne1 == null)
            throw new RuntimeException("Ligne "+ligne.getId() +" not found");
       return initialiseVoyage(ligne1.getDepart(),ligne1.getDestination(),username);
    }

    @Override
    public Optional<Voyage> findById(String id) {
        return voyageRepository.findById(id);
    }

    @Override
    public List<Voyage> listeByLigne(String ligne) {
        // TODO Ajouter un recherche by ligne
        return (List<Voyage>) initialiseVoyage(null,null,null);
    }

    @Override
    public List<Voyage> depToDest(String startWith, String endWith) {
        return voyageRepository.getFindAndEnd(startWith,endWith);

    }

    @Override
    public Iterable<Voyage> allPassBy(String dep, String dest) {
        // TODO Ajouter un recherche by ligne
        return voyageRepository.findAll();
    }
    @Override
    public Voyage initPlaces(Voyage voyage, Vehicule vehicule) {
        Map<String, Place> places = new HashMap<>();
        TypeVoiture typeVoiture = vehicule.getTypeVoiture();
        int nbLigne = typeVoiture.getNbLine();
        int nbRange = typeVoiture.getNbRange();
        // Pour les place au devant
        places.put("0-1", new Place(0, 1));
        places.put("0-2", new Place(0, 2));
        for (int x = 1; x <= nbLigne; x++) {
            for (int y = 1; y <= nbLigne; y++) {
                places.put(x + "-" + y, new Place(x, y));
            }
        }
        List<Place> newPlaces = new ArrayList<>();
        for(Place place : places.values()){
            newPlaces.add(placeRepository.save(place));
        }
        voyage.setPlaces(newPlaces);
       return  voyageRepository.save(voyage);
    }
    public List<Voyage> getFindAndEnd(String startWith, String endWith){
        return voyageRepository.getFindAndEnd(startWith,endWith);
    }
}
