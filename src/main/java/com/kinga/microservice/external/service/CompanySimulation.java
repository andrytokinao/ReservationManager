package com.kinga.microservice.external.service;

import com.kinga.microservice.external.service.modele.Company;
import com.kinga.microservice.external.service.modele.Cooperative;
import com.kinga.microservice.external.service.modele.Ligne;
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
            lignes.add(new Ligne("01" + i, "Ligne for test"));
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
}
