package com.example.apppeluquera.model;

public class Hora {
    String hora;

    public Hora (){}

    public Hora(String hora) {
        this.hora = hora;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Hora{" +
                "hora='" + hora + '\'' +
                '}';
    }
}
