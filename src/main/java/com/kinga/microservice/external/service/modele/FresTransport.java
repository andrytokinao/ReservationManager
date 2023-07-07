package com.kinga.microservice.external.service.modele;

import java.math.BigDecimal;

public class FresTransport {
    String categori ;
    Lieu depart ;
    Lieu destination ;
    BigDecimal prix ;

    public FresTransport() {
    }

    public FresTransport(String categori, Lieu depart, Lieu destination, BigDecimal prix) {
        this.categori = categori;
        this.depart = depart;
        this.destination = destination;
        this.prix = prix;
    }

    public String getCategori() {
        return categori;
    }

    public void setCategori(String categori) {
        this.categori = categori;
    }

    public Lieu getDepart() {
        return depart;
    }

    public void setDepart(Lieu depart) {
        this.depart = depart;
    }

    public Lieu getDestination() {
        return destination;
    }

    public void setDestination(Lieu destination) {
        this.destination = destination;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "FresTransport{" +
                "categori='" + categori + '\'' +
                ", depart=" + depart.getName() +" -->" + destination.getName() +
                ": " + prix +
                '}';
    }
}
