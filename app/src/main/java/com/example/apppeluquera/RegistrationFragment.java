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

import com.example.apppeluquera.databinding.FragmentRegistrationBinding;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationFragment extends BaseFragment {

    private FragmentRegistrationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentRegistrationBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //registro
        binding.buttonUserRegistration.setOnClickListener(view1 -> {
            String email = binding.editTextuserRegisterEmail.getText().toString();
            String password = binding.editTextuserRegisterPassword.getText().toString();
            String confirmpassword = binding.editTextuserRegisterPasswordConfirm.getText().toString();
            String username = binding.editTextuserRegisterUserName.getText().toString();

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
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                nav.navigate(R.id.action_loginFragment_to_menuFragment);
                            } else {
                                Toast.makeText(requireContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}