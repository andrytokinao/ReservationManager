package com.kinga.microservice.reservation.converters;


import com.kinga.microservice.reservation.modeles.VoyageurForm;
import com.kinga.microservice.reservation.domain.Voyageur;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 1/10/17.
 */
@Component
public class FromVoyageur implements Converter<Voyageur, VoyageurForm> {
    @Override
    public VoyageurForm convert(Voyageur voyageur) {
        VoyageurForm voyageurForm = new VoyageurForm();
        voyageurForm.setId(voyageur.getId().toHexString());
        voyageurForm.setContact(voyageur.getContact());
        voyageurForm.setFirstName(voyageur.getFirstName());
        return voyageurForm;
    }
}
