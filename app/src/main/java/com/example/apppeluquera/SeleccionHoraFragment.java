package com.example.apppeluquera;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.apppeluquera.databinding.FragmentSeleccionHoraBinding;
import com.example.apppeluquera.model.Hora;


public class SeleccionHoraFragment extends DialogFragment {

    private FragmentSeleccionHoraBinding binding;
    private AppViewModel appViewModel;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentSeleccionHoraBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        appViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        binding.timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                appViewModel.horaMutableLiveData.setValue(new Hora(hourOfDay, minute));
            }

        });


        binding.timePicker.setOnTimeChangedListener((view1, hourOfDay, minute)
                -> appViewModel.horaMutableLiveData.setValue(new Hora(hourOfDay, minute)));
    }
}