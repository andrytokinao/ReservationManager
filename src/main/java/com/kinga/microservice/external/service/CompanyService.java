package com.kinga.microservice.external.service;

import com.kinga.microservice.external.service.modele.Company;
import com.kinga.microservice.external.service.modele.Cooperative;
import com.kinga.microservice.external.service.modele.Ligne;

import java.util.List;

public interface CompanyService {
    public Company getCompanyByUsername(String username);
    public Cooperative getCooperativeByUsername(String username);
    public Ligne getDefaultLigneByCompany(String idCompany);
    public Ligne getDefaultLigneByCooperative(String idCooperative);
    public List<Ligne> ligneDispoCooperative(String idCooperative);
    public List<Ligne> ligneDispoCompany(String idCompany);
}
