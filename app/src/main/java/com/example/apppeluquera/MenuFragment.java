package com.example.apppeluquera;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apppeluquera.databinding.FragmentMenuBinding;

public class MenuFragment extends BaseFragment {

    private FragmentMenuBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentMenuBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.button.setOnClickListener(view1 ->
                nav.navigate(R.id.action_menuFragment_to_seleccionPeluqueriaFragment));
        binding.button2.setOnClickListener(view12 ->
                nav.navigate(R.id.action_menuFragment_to_consultDateFragment));
        binding.button3.setOnClickListener(view13 ->
                nav.navigate(R.id.action_menuFragment_to_contactanosFragment));

    }

}