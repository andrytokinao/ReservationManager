package com.kinga.microservice.reservation.domain;

public enum TypeVoiture {
    Type46(4,6),
    Type56(5,6),
    Type45(4,5);

    private Integer nbPlaces;
    private  Integer nbRange;
    TypeVoiture(Integer nbPlaces, Integer nbRange) {
        this.nbPlaces = nbPlaces;
        this.nbRange = nbRange;
    }


}
