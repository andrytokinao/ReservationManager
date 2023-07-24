package com.kinga.microservice.reservation.modeles;

public class ReservedStatus {
    public static String STATUS_SUCCESS = "success";
    public static String STATUS_QUASI_DISPONIBLE = "quasi-disopnible";
    public static String STATUS_DISPONIBLE = "disponible";
    public static String STATUS_NOT_DISPONIBLE = "not-disponible";
    public static String STATUS_ERROR = "error";
    String status ;
    String messages;

    public ReservedStatus() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public ReservedStatus(String status, String messages) {
        this.status = status;
        this.messages = messages;
    }
}
