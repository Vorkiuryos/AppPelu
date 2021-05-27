package com.example.apppeluquera;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apppeluquera.databinding.FragmentMenuBinding;

public class MenuFragment extends BaseFragment {

    private FragmentMenuBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMenuBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                nav.navigate(R.id.action_menuFragment_to_loginFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        binding.buttonPedirCita.setOnClickListener(view1 ->
                nav.navigate(R.id.action_menuFragment_to_seleccionPeluqueriaFragment));
        binding.button2.setOnClickListener(view12 ->
                nav.navigate(R.id.action_menuFragment_to_consultDateFragment));
        binding.buttonExpositorArticulos.setOnClickListener(view13 ->
                nav.navigate(R.id.action_menuFragment_to_productosFragment));
        binding.buttonGestionarInfo.setOnClickListener(view13 ->
                Toast.makeText(requireContext(), "Estamos trabajando en ello", Toast.LENGTH_SHORT).show());

    }

}