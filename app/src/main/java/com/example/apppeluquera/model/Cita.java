package com.example.apppeluquera.model;

public class Cita {
    String id_peluqueria;
    String id_servicio;
    String id_usuario;
    Fecha fecha;
    Hora hora;

    public Cita(String id_peluqueria, String id_servicio, String id_usuario, Fecha fecha, Hora hora) {
        this.id_peluqueria = id_peluqueria;
        this.id_servicio = id_servicio;
        this.id_usuario = id_usuario;
        this.fecha = fecha;
        this.hora = hora;
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
