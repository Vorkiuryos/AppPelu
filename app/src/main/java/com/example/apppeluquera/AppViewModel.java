package com.example.apppeluquera;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apppeluquera.model.Cita;
import com.example.apppeluquera.model.Fecha;
import com.example.apppeluquera.model.Hora;
import com.example.apppeluquera.model.Peluqueria;
import com.example.apppeluquera.model.Servicio;


public class AppViewModel extends ViewModel {

    String peluqueria = "5FBzk6ANkRsIzVZ4R6b0"; //TODO
    String nombrepeluqueria = "la pelu de la melu"; //TODO
    MutableLiveData<Fecha> fechaMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Hora> horaMutableLiveData =  new MutableLiveData<>();
    MutableLiveData<Servicio> servicioMutableLiveData = new MutableLiveData<>();

    MutableLiveData<Cita> citaMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Peluqueria> peluqueriaMutableLiveData = new MutableLiveData<>();



}
