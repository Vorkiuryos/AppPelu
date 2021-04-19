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

import com.example.apppeluquera.databinding.FragmentLoginBinding;
import com.example.apppeluquera.databinding.FragmentRegistrationBinding;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends BaseFragment {

    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentLoginBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.login.setOnClickListener(view1 -> {
            String email = LoginFragment.this.binding.editTextEmailAdress.getText().toString();
            String password_ = binding.editTextPassword.getText().toString();
            if(email.isEmpty()){
                //error.setText("Introduce tu correo electrónico");
            } else if (email.contains("@") != true){
                //error.setText("Introduce un correo electrónico válido");
            } else if (password_.isEmpty()){
                //error.setText("Introduce tu contraseña");
            } else {
                //navController.navigate(R.id.action_loginFragment_to_menuFragment);
                auth.signInWithEmailAndPassword(email, password_)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                nav.navigate(R.id.action_loginFragment_to_menuFragment);
                            } else {
                                Toast.makeText(requireContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        binding.goToRegistration.setOnClickListener(view2 -> {
            nav.navigate(R.id.action_loginFragment_to_registrationOptionFragment);
        });

    }
}
