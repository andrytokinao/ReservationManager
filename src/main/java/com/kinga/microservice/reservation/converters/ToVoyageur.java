package com.kinga.microservice.reservation.converters;


import com.kinga.microservice.reservation.modeles.VoyageurForm;
import com.kinga.microservice.reservation.domain.Voyageur;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by jt on 1/10/17.
 */
@Component
public class ToVoyageur implements Converter<VoyageurForm, Voyageur> {

    @Override
    public Voyageur convert(VoyageurForm voyageurForm) {
        Voyageur voyageur = new Voyageur();
        if (voyageurForm.getId() != null  && !StringUtils.isEmpty(voyageurForm.getId())) {
            voyageur.setId(new ObjectId(voyageurForm.getId()));
        }
        voyageur.setContact(voyageurForm.getContact());
        voyageur.setIdClient(voyageurForm.getIdClient());

        return voyageur;
    }
}
