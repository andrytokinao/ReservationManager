package com.kinga.microservice.reservation.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transferred")
public class Transferred extends Reserved{


}
