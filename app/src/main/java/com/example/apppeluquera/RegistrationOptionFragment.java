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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrationOptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationOptionFragment extends Fragment {

    Button register_admin_button;
    Button register_user_button;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration_option, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        navController = Navigation.findNavController(view);
        register_admin_button = view.findViewById(R.id.register_admin_button);
        register_user_button = view.findViewById(R.id.register_user_button);

        register_admin_button.setOnClickListener(view1 -> {
            navController.navigate(R.id.action_registrationOptionFragment_to_adminRegistrationFragment);
        });

        register_user_button.setOnClickListener(view2 -> {
            navController.navigate(R.id.action_registrationOptionFragment_to_registrationFragment);
        });


    }
}