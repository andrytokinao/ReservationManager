package com.kinga.microservice.external.service.modele;

import org.apache.commons.lang3.StringUtils;

public class Lieu {
    String id ;
    String name;

    public Lieu() {
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Lieu))
            return false;
        return StringUtils.equals(this.getId(),((Lieu) obj).getId());
    }

    public Lieu(String id, String name) {
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
