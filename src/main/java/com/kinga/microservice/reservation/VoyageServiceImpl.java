package com.kinga.microservice.reservation;

import com.kinga.microservice.external.service.CompanyService;
import com.kinga.microservice.external.service.DirectionService;
import com.kinga.microservice.external.service.modele.Company;
import com.kinga.microservice.external.service.modele.Cooperative;
import com.kinga.microservice.reservation.domain.Voyage;
import com.kinga.microservice.external.service.modele.Ligne;
import com.kinga.microservice.external.service.modele.Lieu;
import com.kinga.microservice.reservation.repositories.VoyageRepository;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
import jdk.jfr.internal.tool.PrettyWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoyageServiceImpl implements VoyageService{
    @Autowired
    VoyageRepository voyageRepository;
    @Autowired
    CompanyService companyService;
    @Autowired
    DirectionService directionService;
    @Override
    public Voyage updateOrCreate(Voyage voyage) {
        // TODO Verification des entreprise , des donnée null , etc , utilisateur
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
        Ligne ligne = directionService.getLigne(depart, destination);
        List<Ligne> ligneDispo = company != null ? companyService.ligneDispoCompany(company.getId()) : companyService.ligneDispoCooperative(cooperative.getId());

        if(!ligneDispo.contains(ligne)){
            throw new RuntimeException("Ligne not accessible for "+companyOrCooperative);
        }
        voyage.setLigne(ligne);
        List<Lieu> escales = (directionService.getEscales(depart,destination));
        voyage.setEscales(escales);
        String escaleString = "";
        for(Lieu p : escales) {
            escaleString+="-"+p.getId();
        }
        voyage.setStringEscales(escaleString);
        voyage.setFresTransports(directionService.getFres(ligne.getId(),"default"));
        return voyage;
    }

    @Override
    public Voyage initialiseVoyage(Ligne ligne, String username) {
        Ligne ligne1 = directionService.getLigneById(ligne.getId());
        if(ligne1 == null)
            throw new RuntimeException("Ligne "+ligne.getId() +" not found");
       return initialiseVoyage(ligne1.getDepart(),ligne1.getDestination(),username);
    }

    @Override
    public Optional<Voyage> findById(String id) {
        return voyageRepository.findById(id);
    }

    @Override
    public List<Voyage> listeByLigne(Ligne ligne) {
        // TODO Ajouter un recherche by ligne
        return (List<Voyage>) initialiseVoyage(null,null,null);
    }

    @Override
    public List<Voyage> depToDest(Lieu dep, Lieu dest) {
       // TODO Ajouter un recherche by ligne
        return (List<Voyage>) initialiseVoyage(null,null,null);
    }

    @Override
    public Iterable<Voyage> allPassBy(List dep, List dest) {
        // TODO Ajouter un recherche by ligne
        return voyageRepository.findAll();
    }

}
