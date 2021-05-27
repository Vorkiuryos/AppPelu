package com.example.apppeluquera;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.apppeluquera.model.Producto;

import java.util.List;

public class ProductosViewModel extends AndroidViewModel {

        RepositorioProductos repositorioProductos;

        Producto productoSeleccionado;

        public ProductosViewModel(@NonNull Application application) {
            super(application);

            repositorioProductos = new RepositorioProductos();
        }


        LiveData<List<Producto>> productos() {
            return repositorioProductos.productos();
        }

        public void setProductoSeleccionado(Producto producto) {
            productoSeleccionado = producto;
        }
}
