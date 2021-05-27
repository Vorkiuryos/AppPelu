package com.example.apppeluquera;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                nav.navigate(R.id.action_menuNegocioFragment_to_loginFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        binding.gestionarCitas.setOnClickListener(v ->
                nav.navigate(R.id.action_menuNegocioFragment_to_gestionarCitaFragment));

        binding.gestionarHorarios.setOnClickListener(v ->
                nav.navigate(R.id.action_menuNegocioFragment_to_gestionarHorariosFragment));

        binding.gestionarInfo.setOnClickListener(v ->
                nav.navigate(R.id.action_menuNegocioFragment_to_gestionarInfoFragment));

        binding.anadirServicio.setOnClickListener(v ->
                nav.navigate(R.id.action_menuNegocioFragment_to_addServicioFragment));

        binding.expositorArticulos.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Disponible en siguientes versiones", Toast.LENGTH_SHORT).show());
    }
}