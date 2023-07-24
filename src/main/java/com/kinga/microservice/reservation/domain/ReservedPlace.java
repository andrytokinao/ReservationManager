package com.kinga.microservice.reservation.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reservedPlace")
public  class ReservedPlace {
    @Id
    private ObjectId id;
    private String idReserved;
    private String place ;
    private Integer indexDepart;
    private Integer indexDestination;

    private String status;
    private String idReservation;
    private String idVoyage;

    public ReservedPlace() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getIdReserved() {
        return idReserved;
    }

    public void setIdReserved(String idReserved) {
        this.idReserved = idReserved;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getIndexDepart() {
        return indexDepart;
    }

    public void setIndexDepart(Integer indexDepart) {
        this.indexDepart = indexDepart;
    }

    public Integer getIndexDestination() {
        return indexDestination;
    }

    public void setIndexDestination(Integer indexDestination) {
        this.indexDestination = indexDestination;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(String idReservation) {
        this.idReservation = idReservation;
    }

    public String getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(String idVoyage) {
        this.idVoyage = idVoyage;
    }
}
