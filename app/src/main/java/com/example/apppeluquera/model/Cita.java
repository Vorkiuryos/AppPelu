package com.example.apppeluquera.model;

import java.util.Date;

public class Cita {
    String id_peluqueria;
    String id_servicio;
    String id_usuario;
    Date fecha;

    public Cita(String id_peluqueria, String id_servicio, String id_usuario, Date fecha) {
        this.id_peluqueria = id_peluqueria;
        this.id_servicio = id_servicio;
        this.id_usuario = id_usuario;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "id_peluqueria='" + id_peluqueria + '\'' +
                ", id_peluquero='" + id_servicio + '\'' +
                ", id_usuario='" + id_usuario + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
