package com.kinga.microservice.reservation.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Document(collection = "reservation")
public class Reservation {
    @Id
    private String id;
    private String numero;
    private String utilisateur;
    private String codeValidation;
    @NotNull
    private Voyage voyage;
    private List<Reserved> reserveds;

    public Reservation() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getCodeValidation() {
        return codeValidation;
    }

    public void setCodeValidation(String codeValidation) {
        this.codeValidation = codeValidation;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    public List<Reserved> getReserveds() {
        return reserveds;
    }

    public void setReserveds(List<Reserved> reserveds) {
        this.reserveds = reserveds;
    }
    // Constructeurs, getters et setters
}
