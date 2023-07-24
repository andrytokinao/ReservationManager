package com.kinga.microservice.external.service;

import com.kinga.microservice.external.service.modele.FresTransport;
import com.kinga.microservice.external.service.modele.Ligne;
import com.kinga.microservice.external.service.modele.Lieu;

import java.util.List;

public interface RouteService {
    public List<Lieu> getEscales(String idLigne);
    public List<Lieu> getEscales(Lieu from,Lieu to);
    public FresTransport getFres(Lieu dep, Lieu dest, String category);
    public List<FresTransport> getFres(String idLigne ,String category);
    public Ligne getLigne(Lieu dep, Lieu dest);
    public Ligne getLigneById(String id);
    public Lieu getLieuById(String id);

}
