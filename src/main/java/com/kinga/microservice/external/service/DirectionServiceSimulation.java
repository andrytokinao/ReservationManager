package com.kinga.microservice.external.service;

import com.kinga.microservice.external.service.modele.FresTransport;
import com.kinga.microservice.external.service.modele.Ligne;
import com.kinga.microservice.external.service.modele.Lieu;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class DirectionServiceSimulation implements DirectionService{

    @Override
    public List<Lieu> getEscales(String idLigne) {
        List<Lieu> lieus = new ArrayList<>();
        for(int i = 1 ; i < 11 ; i++){
            lieus.add(new Lieu("0"+i,"Lieu "+i));
        }
        return lieus;
    }

    @Override
    public List<Lieu> getEscales(Lieu from, Lieu to) {
        List<Lieu> lieus = new ArrayList<>();
        for(int i = 1 ; i < 11 ; i++){
            lieus.add(new Lieu("0"+i,"Lieu "+i));
        }
        return lieus;
    }

    @Override
    public FresTransport getFres(Lieu dep, Lieu dest, String category) {
        return new FresTransport("Prix test",new Lieu("10","Lieu depart"), new Lieu("02","Lieu dest"),new BigDecimal(5000));
    }

    @Override
    public List<FresTransport> getFres(String idLigne, String category) {
        BigDecimal prix = new BigDecimal(40000);
        List<FresTransport> fresTransports = new ArrayList<>();
        List<Lieu> lieus = getEscales("RN 7");
        for(int i = 0 ; i < lieus.size(); i ++) {
            Lieu depart = lieus.get(i);
            for (int j = i + 1; j < lieus.size(); j++) {
                Lieu dest = lieus.get(j);
                BigDecimal maxDistance = new BigDecimal(lieus.size());
                BigDecimal distance = new BigDecimal(j).subtract(new BigDecimal(i));
                int scale = 2; // Desired scale (number of decimal lieus)
                RoundingMode roundingMode = RoundingMode.HALF_UP; // Desired rounding mode
                BigDecimal px = prix.multiply(distance).divide(maxDistance, scale, roundingMode);
                fresTransports.add(new FresTransport("prix test ", depart, dest, px));
            }
        }
        return fresTransports ;
    }

    @Override
    public Ligne getLigne(Lieu dep, Lieu dest) {
        Ligne ligne = new  Ligne("01","Default");
        ligne.setDepart(new Lieu("01","Lieu 1"));
        ligne.setDestination(new Lieu("10","Lieu 10"));
        return ligne ;
    }
        @Override
    public Ligne getLigneById(String id) {
       Ligne ligne = new  Ligne("01","Default");
        ligne.setDepart(new Lieu("01","Lieu 1"));
        ligne.setDestination(new Lieu("10","Lieu 10"));
        return ligne ;
    }

    @Override
    public Lieu getLieuById(String id) {
        return new Lieu("01","Lieu ");

    }
}
