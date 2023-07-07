package com.kinga.microservice.reservation.domain;

import com.kinga.microservice.external.service.modele.FresTransport;
import com.kinga.microservice.external.service.modele.Ligne;
import com.kinga.microservice.external.service.modele.Lieu;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "voyage")
public class Voyage {

    private String company;
    private Vehicule vehicule;

    private String numero;
    private Ligne ligne ;

    private String numeroVehicule;

    private String stringEscales;
    private List<Lieu> escales;
    private List<FresTransport> fresTransports;

    private List<Reservation> reservations;




    public Voyage() {
    }


    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }


    public String getNumeroVehicule() {
        return numeroVehicule;
    }

    public void setNumeroVehicule(String numeroVehicule) {
        this.numeroVehicule = numeroVehicule;
    }

    public String getStringEscales() {
        return stringEscales;
    }

    public void setStringEscales(String stringEscales) {
        this.stringEscales = stringEscales;
    }

    public List<Lieu> getEscales() {
        return escales;
    }

    public void setEscales(List<Lieu> escales) {
        this.escales = escales;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Ligne getLigne() {
        return ligne;
    }

    public void setLigne(Ligne ligne) {
        this.ligne = ligne;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    public List<FresTransport> getFresTransports() {
        return fresTransports;
    }
    public Vehicule getVehicule() {
        return vehicule;
    }
    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }
    public void setFresTransports(List<FresTransport> fresTransports) {
        this.fresTransports = fresTransports;
    }
}
