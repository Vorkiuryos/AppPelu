package com.example.apppeluquera;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apppeluquera.databinding.FragmentGestionInfoCitaBinding;
import com.example.apppeluquera.databinding.FragmentInfoCitaBinding;


public class GestionInfoCitaFragment extends BaseFragment {

    private FragmentGestionInfoCitaBinding binding;
    private AppViewModel appViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentGestionInfoCitaBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        appViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        binding.nombreCliente.setText(appViewModel.citaMutableLiveData.getValue().getNombrePeluqueria());
        binding.diaCita.setText("TEST/TEST/TEST");
        binding.horaCita.setText("TEST : TEST");
        binding.serviciosCita.setText(appViewModel.citaMutableLiveData.getValue().getNombreServicio());

        binding.canelarCita.setOnClickListener(v -> {
            //TODO Comprobación de si quieres borrar realmente la cita
            db.collection("users").document(auth.getUid()).collection("citas").document(appViewModel.citaMutableLiveData.getValue().getId()).delete();
            nav.navigate(R.id.consultDateFragment);

        });

    }
}