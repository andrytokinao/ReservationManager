package com.kinga.microservice.reservation.domain;

import com.kinga.microservice.external.service.modele.Lieu;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Document(collection = "reserved")
public abstract class Reserved {
    @Id
    private ObjectId _id;
    @NotNull
    private String user;
    private Lieu depart;
    private BigDecimal fres;
    private Lieu destination;
    private List<ReservedPlace> reservedPlace ;
    private String type;
    private String  idReservation;
    private String idVoyage;

    public Reserved() {
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public List<ReservedPlace> getReservedPlace() {
        return reservedPlace;
    }

    public void setReservedPlace(List<ReservedPlace> reservedPlace) {
        this.reservedPlace = reservedPlace;
    }

    public String getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(String idVoyage) {
        this.idVoyage = idVoyage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(String idReservation) {
        this.idReservation = idReservation;
    }

    public BigDecimal getFres() {
        return fres;
    }

    public void setFres(BigDecimal fres) {
        this.fres = fres;
    }
}
