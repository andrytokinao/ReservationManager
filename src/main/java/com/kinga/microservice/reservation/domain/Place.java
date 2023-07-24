package com.kinga.microservice.reservation.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection = "place")
public class Place {
    @Id
    private ObjectId id;
    public static String ETAT_DISPONIBLE = "disponible";
    public static String ETAT_RESERVED = "reserved";
    public static String ETAT_QUASI_RESERVED = "quasi_disponible";
    public static String ETAT_DISABLED = "disabled";
    private int x ;
    private int y ;
    private String etat = ETAT_DISPONIBLE;
    private List<ReservedPlace> reservedPlaces ;

    public Place() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Place(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Place)){
            return false;
        }
        Place p = (Place) obj;
        return (this.getX() == p.getX()) && this.getY() == p.getY();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
       return this.getX()+"-"+this.getY();

    }

    public List<ReservedPlace> getReservedPlaces() {
        return reservedPlaces;
    }

    public void setReservedPlaces(List<ReservedPlace> reservedPlaces) {
        this.reservedPlaces = reservedPlaces;
    }
}
