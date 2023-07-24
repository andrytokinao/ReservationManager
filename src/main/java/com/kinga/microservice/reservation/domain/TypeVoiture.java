package com.kinga.microservice.reservation.domain;

import java.util.ArrayList;
import java.util.List;

public class TypeVoiture {
    String id ;
    String name;
    String marque;
    Integer nbLine = 5;
    Integer nbRange = 4 ;

    public TypeVoiture() {
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

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Integer getNbLine() {
        return nbLine;
    }

    public void setNbLine(Integer nbLine) {
        this.nbLine = nbLine;
    }

    public Integer getNbRange() {
        return nbRange;
    }

    public void setNbRange(Integer nbRange) {
        this.nbRange = nbRange;
    }

    public List<Place> getAllPlace(){
        List<Place> places =new ArrayList<>();
        places.add(new Place(0,1));
        places.add(new Place(0,2));
        for(int i = 1 ; i <= nbLine ; i++){
            for(int j = 1 ; j<= nbRange ; j++){
                places.add(new Place(i,j));
            }
        }
        return places;
    }


}
