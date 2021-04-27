package com.example.apppeluquera;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apppeluquera.databinding.FragmentAdminRegistrationBinding;
import com.example.apppeluquera.databinding.FragmentRegistrationBinding;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.HashMap;
import java.util.Map;

public class AdminRegistrationFragment extends BaseFragment {

    private FragmentAdminRegistrationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentAdminRegistrationBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonAdminRegistration.setOnClickListener(view1 -> {
            String email = binding.editTextadminRegisterEmail.getText().toString();
            String password = binding.editTextadminRegisterPassword.getText().toString();
            String confirmpassword = binding.editTextadminRegisterPasswordConfirm.getText().toString();
            String username = binding.editTextadminRegisterUserName.getText().toString();


            if(email.isEmpty()){
                //error.setText("Introduce tu correo electrónico");
            } else if (!email.contains("@")){
                //error.setText("Introduce un correo electrónio válido");
            } else if (password.isEmpty()) {
                //error.setText("Introduce tu contraseña");
            } else if (!password.equals(confirmpassword)) {
                //error.settext("la contrasñea es diferente a la cofnirmada");
            } else if (username.isEmpty()) {
                //error introduce usuario
            } else {

                createAccount(email,password,username);
            }
        });
    }

    private void createAccount(String email, String password, String username) {

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task ->{
            if (task.isSuccessful()) {
                Toast.makeText(requireContext(), "successful", Toast.LENGTH_SHORT).show();
                nav.navigate(R.id.action_adminRegistrationFragment_to_loginFragment);

                Map<Object, String> data = new HashMap<>();
                data.put("type", "business");
                db.collection("users").document(auth.getCurrentUser().getUid()).set(data);

                auth.getCurrentUser().updateProfile(
                        new UserProfileChangeRequest.Builder()
                                .setDisplayName(username)
                                .build()
                );



            } else {
                Toast.makeText(requireContext(), "algo ha ido mal", Toast.LENGTH_SHORT).show();
            }
        });

    }


}