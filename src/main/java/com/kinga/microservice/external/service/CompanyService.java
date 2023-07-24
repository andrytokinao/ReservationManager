package com.kinga.microservice.external.service;

import com.kinga.microservice.external.service.modele.Company;
import com.kinga.microservice.external.service.modele.Cooperative;
import com.kinga.microservice.external.service.modele.Ligne;
import com.kinga.microservice.reservation.domain.Personne;
import com.kinga.microservice.reservation.domain.Vehicule;

import java.util.List;

public interface CompanyService {
    public Company getCompanyByUsername(String username);
    public Cooperative getCooperativeByUsername(String username);
    public Ligne getDefaultLigneByCompany(String idCompany);
    public Ligne getDefaultLigneByCooperative(String idCooperative);
    public List<Ligne> ligneDispoCooperative(String idCooperative);
    public List<Ligne> ligneDispoCompany(String idCompany);
    public List<Company> companyDispoInCooperative(String idCompany);
    public List<Vehicule> getVehicules(String idCompany);
    public List<Personne> getChauffeurs(String idCompany);
    public List<Personne> getResponsablesByCompany(String idCompany);
    public List<Personne> getResponsablesByCooperative(String idCooperative);
}
