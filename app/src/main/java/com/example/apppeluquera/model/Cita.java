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

    public String getId_peluqueria() {
        return id_peluqueria;
    }

    public String getId_servicio() {
        return id_servicio;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public Hora getHora() {
        return hora;
    }
}
