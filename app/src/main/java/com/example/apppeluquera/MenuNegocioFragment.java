package com.example.apppeluquera;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apppeluquera.databinding.FragmentMenuBinding;
import com.example.apppeluquera.databinding.FragmentMenuNegocioBinding;


public class MenuNegocioFragment extends BaseFragment {

    private FragmentMenuNegocioBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMenuNegocioBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.gestionarCitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav.navigate(R.id.action_menuNegocioFragment_to_gestionarCitaFragment);
            }
        });

        binding.gestionarHorarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav.navigate(R.id.action_menuNegocioFragment_to_gestionarHorariosFragment);
            }
        });

        binding.gestionarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav.navigate(R.id.action_menuNegocioFragment_to_gestionarInfoFragment);
            }
        });

        binding.anadirServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav.navigate(R.id.action_menuNegocioFragment_to_addServicioFragment);
            }
        });

        binding.expositorArtículos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
    }
}