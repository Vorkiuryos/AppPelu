package com.example.apppeluquera;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.apppeluquera.model.Producto;

import java.util.Arrays;
import java.util.List;

public class RepositorioProductos {
    LiveData<List<Producto>> productosLiveData;

    RepositorioProductos() {

        List<Producto> albums = Arrays.asList(
                new Producto("Espray voluminizante Biokera", "Da volumen a tu pelo", R.drawable.biokera_espary_volumizante),
                new Producto("Espuma fuerte Kadus", "Espuma con fijación de larga duración", R.drawable.kadus_espuma_fuerte),
                new Producto("Espuma rizos Rizos", "Espuma para rizos fuertes", R.drawable.kadus_espuma_rizos),
                new Producto("Minerales estimulantes kadus", "Cuida tu cuero cabelludo", R.drawable.kadus_minerales_estimulantes),
                new Producto("Espuma hondas Salerm", "Potencia tus hondas", R.drawable.salerm_espuma_hondas),
                new Producto("Gomina Salerm", "La mejor gomina del mercado", R.drawable.salerm_gomina)

        );

        productosLiveData = new MutableLiveData<>(albums);
    }


    LiveData<List<Producto>> productos() {
        return productosLiveData;
    }
}
