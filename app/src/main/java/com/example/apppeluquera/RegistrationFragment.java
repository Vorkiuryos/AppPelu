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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class RegistrationFragment extends Fragment {

    private FirebaseAuth mAuth;
    Button buttonUserRegistration;
    EditText editTextuserRegisterEmail,
            editTextuserRegisterPassword,
            editTextuserRegisterPasswordConfirm,
            editTextuserRegisterUserName;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonUserRegistration = view.findViewById(R.id.buttonUserRegistration);
        editTextuserRegisterEmail = view.findViewById(R.id.editTextuserRegisterEmail);
        editTextuserRegisterPassword = view.findViewById(R.id.editTextuserRegisterPassword);
        editTextuserRegisterPasswordConfirm = view.findViewById(R.id.editTextuserRegisterPasswordConfirm);
        editTextuserRegisterUserName = view.findViewById(R.id.editTextuserRegisterUserName);
        navController = Navigation.findNavController(view);

        //registro
        buttonUserRegistration.setOnClickListener(view1 -> {
            String email = editTextuserRegisterEmail.getText().toString();
            String password = editTextuserRegisterPassword.getText().toString();
            String confirmpassword = editTextuserRegisterPasswordConfirm.getText().toString();
            String username = editTextuserRegisterUserName.getText().toString();


            if(email.isEmpty()){
                //error.setText("Introduce tu correo electrónico");
            } else if (!email.contains("@")){
                //error.setText("Introduce un correo electrónio válido");
            } else if (password.isEmpty()) {
                //error.setText("Introduce tu contraseña");
            } else if (password != confirmpassword) {
                //error.settext("la contrasñea es diferente a la cofnirmada");
            } else if (username.isEmpty()) {
                //error introduce usuario
            } else {
                //navController.navigate(R.id.action_loginFragment_to_menuFragment);
                //TODO: REGISTRO CON EMAIL
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                navController.navigate(R.id.action_loginFragment_to_menuFragment);
                            } else {
                                Toast.makeText(requireContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        });


    }
}