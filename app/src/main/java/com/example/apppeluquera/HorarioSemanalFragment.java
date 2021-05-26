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
                nav.navigate(R.id.action_horarioSemanalFragment_to_addHoraFragment);
                appViewModel.diaHorarioString.setValue("lunes");
            }
        });

        binding.martes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav.navigate(R.id.action_horarioSemanalFragment_to_addHoraFragment);
                appViewModel.diaHorarioString.setValue("martes");
            }
        });

        binding.miercoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav.navigate(R.id.action_horarioSemanalFragment_to_addHoraFragment);
                appViewModel.diaHorarioString.setValue("miercoles");
            }
        });

        binding.jueves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav.navigate(R.id.action_horarioSemanalFragment_to_addHoraFragment);
                appViewModel.diaHorarioString.setValue("jueves");
            }
        });

        binding.viernes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav.navigate(R.id.action_horarioSemanalFragment_to_addHoraFragment);
                appViewModel.diaHorarioString.setValue("viernes");
            }
        });

        binding.sabado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav.navigate(R.id.action_horarioSemanalFragment_to_addHoraFragment);
                appViewModel.diaHorarioString.setValue("sabado");
            }
        });

        binding.domingo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav.navigate(R.id.action_horarioSemanalFragment_to_addHoraFragment);
                appViewModel.diaHorarioString.setValue("domingo");
            }
        });

    }
}