package com.ttd.manage_ms.models;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Delivery{

    @Id
    private String id;
    private String usernameEmisor;
    private String usernameReceptor;
    private String ciudadOrigen;
    private String ciudadDestino;
    private String direccionOrigen;
    private String direccionDestino;
    private int value;
    private String description;
    private String estado;
    private Date pickUpDate;
    private Date deliverDate;
    private String pqr;

    public Delivery(String id, String usernameEmisor, String usernameReceptor, String ciudadOrigen,
                    String ciudadDestino, String direccionOrigen, String direccionDestino, int value,
                    String description, String estado, Date pickUpDate, Date deliverDate, String pqr){
        this.id = id;
        this.usernameEmisor = usernameEmisor;
        this.usernameReceptor = usernameReceptor;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.direccionOrigen = direccionOrigen;
        this.direccionDestino = direccionDestino;
        this.value = value;
        this.description = description;
        this.estado = estado;
        this.pickUpDate = pickUpDate;
        this.deliverDate = deliverDate;
        this.pqr = pqr;
    }


    public String getId() {
        return id;
    }
    public String getUsernameEmisor() {
        return usernameEmisor;
    }
    public void setUsernameEmisor(String usernameEmisor) {
        this.usernameEmisor = usernameEmisor;
    }
    public String getUsernameReceptor() {
        return usernameReceptor;
    }
    public void setUsernameReceptor(String usernameReceptor) {
        this.usernameReceptor = usernameReceptor;
    }
    public String getCiudadOrigen() {
        return ciudadOrigen;
    }
    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }
    public String getCiudadDestino() {
        return ciudadDestino;
    }
    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }
    public String getDireccionOrigen() {
        return direccionOrigen;
    }
    public void setDireccionOrigen(String direccionOrigen) {
        this.direccionOrigen = direccionOrigen;
    }
    public String getDireccionDestino() {
        return direccionDestino;
    }
    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getPickUpDate() {
        return pickUpDate;
    }
    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }
    public Date getDeliverDate() {
        return deliverDate;
    }
    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }
    public String getPqr() {
        return pqr;
    }
    public void setPqr(String pqr) {
        this.pqr = pqr;
    }

}
