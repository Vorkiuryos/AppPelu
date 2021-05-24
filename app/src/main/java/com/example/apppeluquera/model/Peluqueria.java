package com.example.apppeluquera.model;

public class Peluqueria {
    String id;
    String nombre;
    String direccion;

    public Peluqueria(){}

    public Peluqueria(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Peluqueria{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
