package com.kinga.microservice.reservation.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "disabled")
public class Disabled extends Reserved {

    public Disabled() {
    }


}
