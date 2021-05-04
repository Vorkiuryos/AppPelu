package com.example.apppeluquera;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.apppeluquera.databinding.FragmentSeleccionFechaBinding;
import com.example.apppeluquera.model.Fecha;


public class SeleccionFechaFragment extends DialogFragment {
    private FragmentSeleccionFechaBinding binding;
    private AppViewModel appViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentSeleccionFechaBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        appViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        binding.calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            appViewModel.fechaMutableLiveData.setValue(new Fecha(year, month, dayOfMonth));
            dismiss();
        });
    }
}