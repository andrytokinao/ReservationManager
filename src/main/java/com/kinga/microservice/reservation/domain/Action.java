package com.kinga.microservice.reservation.domain;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "actions")
public class Action {
    @Id
    private ObjectId id;
    private String type;
    private Date date;
    private String user;
    @DBRef
    private Reserved reserved;

    public Action() {
    }

    public ObjectId getId() {
        return id;
    }

    public String getStringId() {
        return getId().toHexString();
    }
    public void setStringId(String id) {
       setId(new ObjectId(id));
    }


    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Reserved getReserved() {
        return reserved;
    }

    public void setReserved(Reserved reserved) {
        this.reserved = reserved;
    }
    // Constructeurs, getters et setters
}

