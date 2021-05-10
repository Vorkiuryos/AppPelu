package com.example.apppeluquera.model;

public class Servicio {
    String id;
    String nombre;

    public Servicio(){}

    public Servicio(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    public String getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
