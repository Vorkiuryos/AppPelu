package com.example.apppeluquera;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apppeluquera.databinding.FragmentGestionarHorariosBinding;
import com.example.apppeluquera.databinding.FragmentMenuNegocioBinding;

public class GestionarHorariosFragment extends BaseFragment {

    private FragmentGestionarHorariosBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentGestionarHorariosBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    }
}