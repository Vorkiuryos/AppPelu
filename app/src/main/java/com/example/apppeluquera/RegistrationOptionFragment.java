package com.example.apppeluquera;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.apppeluquera.databinding.FragmentLoginBinding;
import com.example.apppeluquera.databinding.FragmentRegistrationOptionBinding;

public class RegistrationOptionFragment extends BaseFragment {

    private FragmentRegistrationOptionBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentRegistrationOptionBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        binding.registerAdminButton.setOnClickListener(view1 -> {
            nav.navigate(R.id.action_registrationOptionFragment_to_adminRegistrationFragment);
        });

        binding.registerUserButton.setOnClickListener(view2 -> {
            nav.navigate(R.id.action_registrationOptionFragment_to_registrationFragment);
        });


    }
}