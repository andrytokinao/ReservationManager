package com.kinga.microservice.external.service.modele;

public class Cooperative {
    String id;
    String name;

    public Cooperative() {
    }

    public Cooperative(String id, String name) {
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
}
