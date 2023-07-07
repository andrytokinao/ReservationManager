package com.kinga.microservice.reservation.modeles;

public class Place {
    private int x ;
    private int y ;

    public Place() {
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
}
