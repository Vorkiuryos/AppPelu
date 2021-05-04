package com.example.apppeluquera;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.apppeluquera.databinding.FragmentPedirCitaBinding;

public class PedirCitaFragment extends BaseFragment {

    private FragmentPedirCitaBinding binding;
    private AppViewModel appViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentPedirCitaBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        appViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        binding.selectDay.setOnClickListener(v -> {
            nav.navigate(R.id.action_pedirCitaFragment_to_seleccionFechaFragment);
        });

       appViewModel.fechaMutableLiveData.observe(getViewLifecycleOwner(), fecha -> {
           if(fecha != null){
               binding.selectHour.setImageResource(R.drawable.hour2);
               binding.selectedHour.setEnabled(true);

               binding.selectHour.setOnClickListener(v -> {
                   nav.navigate(R.id.action_pedirCitaFragment_to_seleccionHoraFragment);
               });
           }
       });

        binding.selectHairdresser.setOnClickListener(v -> {
            nav.navigate(R.id.action_pedirCitaFragment_to_seleccionServicioFragment);
        });




        binding.appointmentButton.setOnClickListener(v -> {

        });


    }
}