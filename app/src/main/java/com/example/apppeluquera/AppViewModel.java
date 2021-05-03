package com.example.apppeluquera;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apppeluquera.model.Fecha;


public class AppViewModel extends ViewModel {
    MutableLiveData<Fecha> fechaMutableLiveData = new MutableLiveData<>();

}
