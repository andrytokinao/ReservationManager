package com.kinga.microservice.reservation.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "voyageur")
public class Voyageur extends Reserved {
    public Voyageur() {
        super();
        setType("Voyageur");
    }
    private String firstName;
    private String lastName;
    private String contact;
    private String idClient;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }
}
