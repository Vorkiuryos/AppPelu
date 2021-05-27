package com.example.apppeluquera;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apppeluquera.databinding.FragmentRegistrationBinding;
import com.example.apppeluquera.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;

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

            System.out.println("p1 " + password);
            System.out.println("p2 " + confirmpassword);

            String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            java.util.regex.Matcher m = p.matcher(email);

            if(email.isEmpty()){
                Toast.makeText(requireContext(), "Introduce un correo", Toast.LENGTH_SHORT).show();
            } else if (!m.matches()){
                Toast.makeText(requireContext(), "Introduce un correo electrónico válido", Toast.LENGTH_SHORT).show();
            } else if (password.isEmpty()) {
                Toast.makeText(requireContext(), "Introduce una contraseña", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmpassword)) {
                Toast.makeText(requireContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            } else if (username.isEmpty()) {
                Toast.makeText(requireContext(), "Introduce tu nombre", Toast.LENGTH_SHORT).show();
            } else {

                createAccount(email,password,username);
            }
        });

    }

    private void createAccount(String email, String password, String username) {

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task ->{
           if (task.isSuccessful()) {
               Toast.makeText(requireContext(), "successful", Toast.LENGTH_SHORT).show();
               nav.navigate(R.id.action_registrationFragment_to_loginFragment);

               db.collection("users").document(auth.getCurrentUser().getUid()).set(new User("user"));

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