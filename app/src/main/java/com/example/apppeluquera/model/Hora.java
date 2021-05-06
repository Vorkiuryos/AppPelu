package com.example.apppeluquera.model;

public class Hora {
    int hora, minuto;

    public Hora(int hora, int minuto) {
        this.hora = hora;
        this.minuto = minuto;
    }

    @Override
    public String toString() {
        if (hora < 9 && minuto < 9) return "0" + hora + ":" + "0" + minuto;
        else if (hora < 9) return "0" + hora + ":" + minuto;
        else if (minuto < 9) return hora + ":" + "0" + minuto;
        else return hora + ":" + minuto;
    }

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }
}
