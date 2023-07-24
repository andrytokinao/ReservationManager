package com.kinga.microservice.external.service;

import com.kinga.microservice.external.service.modele.Company;
import com.kinga.microservice.external.service.modele.Cooperative;
import com.kinga.microservice.external.service.modele.Ligne;
import com.kinga.microservice.reservation.domain.Personne;
import com.kinga.microservice.reservation.domain.TypeVoiture;
import com.kinga.microservice.reservation.domain.Vehicule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanySimulation implements CompanyService{
    @Override
    public Company getCompanyByUsername(String username) {
        return new Company("-1","Company for test");
    }

    @Override
    public Cooperative getCooperativeByUsername(String username) {
      return new Cooperative("-1","Cooperative for Test");
    }

    @Override
    public Ligne getDefaultLigneByCompany(String idCompany) {
        return  new Ligne("01","Ligne for test");
    }

    @Override
    public Ligne getDefaultLigneByCooperative(String idCooperative) {
        return  new Ligne("01","Ligne for test");
    }

    @Override
    public List<Ligne> ligneDispoCooperative(String idCooperative) {
        List<Ligne> lignes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            lignes.add(new Ligne("0" + i, "Ligne for test "+i));
        }
        return lignes;
    }
    @Override
    public List<Ligne> ligneDispoCompany(String idCompany) {
        List<Ligne> lignes = new ArrayList<>();
            for(int i = 0 ; i< 5 ; i++){
                lignes.add(new Ligne("0"+i,"Default"));
            }
            return lignes;
    }

    @Override
    public List<Company> companyDispoInCooperative(String idCompany) {
        List<Company> companies = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Company company = new Company();
            company.setId("0"+i);
            company.setName("Company for cooperative test "+i);
            companies.add(company);
        }
        return companies;
    }
    @Override
    public List<Vehicule> getVehicules(String idCompany) {
        List<Vehicule> vehicules = new ArrayList<>();
        for(int i = 1; i<5; i++) {
            Vehicule vehicule = new Vehicule();
            vehicule.setId("0" + i);
            vehicule.setMarque("MARQUE");
            TypeVoiture typeVoiture = new TypeVoiture();
            typeVoiture.setId("00");
            typeVoiture.setMarque("MARQUE");
            typeVoiture.setNbLine(6);
            typeVoiture.setNbRange(5);
            vehicule.setTypeVoiture(typeVoiture);
            vehicules.add(vehicule);
        }
        return vehicules;
    }

    @Override
    public List<Personne> getChauffeurs(String idCompany) {
        List<Personne> chauffeurs = new ArrayList<>();
        for(int i = 1 ; i< 3; i++){
            Personne personne = new Personne();
            personne.setId("0"+i);
            personne.setName("Chauffeurs "+i);
            chauffeurs.add(personne);
        }
        return chauffeurs;
    }

    @Override
    public List<Personne> getResponsablesByCompany(String idCompany) {
        List<Personne> responsables = new ArrayList<>();
        for(int i = 1 ; i< 5; i++){
            Personne personne = new Personne();
            personne.setId("0"+i);
            personne.setName("Responsable company"+i);
            responsables.add(personne);
        }
        return responsables;
    }

    @Override
    public List<Personne> getResponsablesByCooperative(String idCooperative) {
        List<Personne> chauffeurs = new ArrayList<>();
        for(int i = 1 ; i< 5; i++){
            Personne personne = new Personne();
            personne.setId("0"+i);
            personne.setName("Responsable cooperative  "+i);
            chauffeurs.add(personne);
        }
        return chauffeurs;
    }
}
