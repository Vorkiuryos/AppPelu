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
import android.widget.EditText;
import android.widget.TextView;

public class LoginFragment extends Fragment {

    EditText email;
    EditText password;
    Button login;
    NavController navController;
    TextView goToRegistration;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        email = view.findViewById(R.id.editTextEmailAdress);
        password = view.findViewById(R.id.editTextPassword);
        login = view.findViewById(R.id.login);
        goToRegistration = view.findViewById(R.id.go_to_registration);
        navController = Navigation.findNavController(view);

        login.setOnClickListener(view1 -> {
            String email = LoginFragment.this.email.getText().toString();
            String password_ = password.getText().toString();
            if(email.isEmpty()){
                //error.setText("Introduce tu correo electrónico");
            } else if (email.contains("@")!=true){
                //error.setText("Introduce un correo electrónio válido");
            } else if (password_.isEmpty()){
                //error.setText("Introduce tu contraseña");
            } else {
                navController.navigate(R.id.action_loginFragment_to_menuFragment);
            }
        });

        goToRegistration.setOnClickListener(view2 -> {
            navController.navigate(R.id.action_loginFragment_to_registrationOptionFragment);
        });

    }
}
