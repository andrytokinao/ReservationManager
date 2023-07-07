package com.kinga.microservice.reservation.modeles;

import com.kinga.microservice.reservation.domain.Reserved;

import java.util.List;

public class ReservedsListe {
    private List<Reserved> full;
    private List<Reserved>  in;
    private List<Reserved> out;

    public ReservedsListe() {
    }

    public List<Reserved> getFull() {
        return full;
    }

    public void setFull(List<Reserved> full) {
        this.full = full;
    }

    public List<Reserved> getIn() {
        return in;
    }

    public void setIn(List<Reserved> in) {
        this.in = in;
    }

    public List<Reserved> getOut() {
        return out;
    }

    public void setOut(List<Reserved> out) {
        this.out = out;
    }
}
