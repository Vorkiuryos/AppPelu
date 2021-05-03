package com.example.apppeluquera;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apppeluquera.model.Fecha;
import com.example.apppeluquera.model.Hora;


public class AppViewModel extends ViewModel {
    MutableLiveData<Fecha> fechaMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Hora> horaMutableLiveData =  new MutableLiveData<>();


}
