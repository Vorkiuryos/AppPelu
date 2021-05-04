package com.example.apppeluquera.model;

public class Hora {
    int hora, minuto;

    public Hora(int hora, int minuto) {
        this.hora = hora;
        this.minuto = minuto;
    }

    @Override
    public String toString() {
        return hora +":"+ minuto;
    }
}
