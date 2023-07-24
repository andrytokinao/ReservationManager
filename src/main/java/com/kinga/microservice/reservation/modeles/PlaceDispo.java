package com.kinga.microservice.reservation.modeles;

import com.kinga.microservice.reservation.domain.Place;

import java.util.List;

public class PlaceDispo {
    List<Place> disponible;
    List<Place> quasiDispo ;
    Integer nombreDisponible;

    public PlaceDispo() {
    }

    public List<Place> getDisponible() {
        return disponible;
    }

    public void setDisponible(List<Place> disponible) {
        this.disponible = disponible;
    }

    public List<Place> getQuasiDispo() {
        return quasiDispo;
    }

    public void setQuasiDispo(List<Place> quasiDispo) {
        this.quasiDispo = quasiDispo;
    }

    public Integer getNombreDisponible() {
        return nombreDisponible;
    }

    public void setNombreDisponible(Integer nombreDisponible) {
        this.nombreDisponible = nombreDisponible;
    }
}
