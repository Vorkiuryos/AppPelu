package com.example.apppeluquera;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apppeluquera.databinding.FragmentGestionarHorariosBinding;
import com.example.apppeluquera.databinding.FragmentHorarioSemanalBinding;


public class HorarioSemanalFragment extends BaseFragment {

    private FragmentHorarioSemanalBinding binding;
    private AppViewModel appViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentHorarioSemanalBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        appViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        binding.lunes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appViewModel.diaHorarioString.setValue("lunes");
                nav.navigate(R.id.action_horarioSemanalFragment_to_addHoraFragment);
            }
        });

        binding.martes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appViewModel.diaHorarioString.setValue("martes");
                nav.navigate(R.id.action_horarioSemanalFragment_to_addHoraFragment);
            }
        });

        binding.miercoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appViewModel.diaHorarioString.setValue("miercoles");
                nav.navigate(R.id.action_horarioSemanalFragment_to_addHoraFragment);
            }
        });

        binding.jueves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appViewModel.diaHorarioString.setValue("jueves");
                nav.navigate(R.id.action_horarioSemanalFragment_to_addHoraFragment);
            }
        });

        binding.viernes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appViewModel.diaHorarioString.setValue("viernes");
                nav.navigate(R.id.action_horarioSemanalFragment_to_addHoraFragment);
            }
        });

        binding.sabado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appViewModel.diaHorarioString.setValue("sabado");
                nav.navigate(R.id.action_horarioSemanalFragment_to_addHoraFragment);
            }
        });

        binding.domingo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appViewModel.diaHorarioString.setValue("domingo");
                nav.navigate(R.id.action_horarioSemanalFragment_to_addHoraFragment);
            }
        });

    }
}