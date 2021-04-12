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
import android.widget.Toast;

public class LoginFragment extends Fragment {

    EditText telefono;
    EditText nombre;
    Button entrar;
    NavController navController;
    TextView error;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        telefono = view.findViewById(R.id.editTextPhone);
        nombre = view.findViewById(R.id.editTextTextPersonName);
        entrar = view.findViewById(R.id.login);
        navController = Navigation.findNavController(view);
        error = view.findViewById(R.id.error);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numero = telefono.getText().toString();
                String nombre1 = nombre.getText().toString();
                if(numero.isEmpty()){
                    error.setText("Introduce un número de teléfono");
                } else if (numero.length()!=9){
                    error.setText("Introduce un número de teléfono válido");
                } else if (nombre1.isEmpty()){
                    error.setText("Introduce tu nombre");
                } else {
                    navController.navigate(R.id.action_loginFragment_to_menuFragment);
                }
            }
        });

    }
}
