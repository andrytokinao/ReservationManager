package com.kinga.microservice.external.service.modele;

import org.apache.commons.lang3.StringUtils;

public class Ligne {
    String id ;
    String name;
    Lieu depart;
    Lieu destination;
    public Ligne() {
    }

    public Ligne(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Ligne ligne = (Ligne) obj;
        return (StringUtils.equalsIgnoreCase(ligne.getId(),this.getId()))&&
                (StringUtils.equalsIgnoreCase(ligne.getName(),this.getName()));
    }
}
