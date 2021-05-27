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
import com.example.apppeluquera.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class AdminRegistrationFragment extends BaseFragment {

    private FragmentAdminRegistrationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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


            String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            java.util.regex.Matcher m = p.matcher(email);

            if(email.isEmpty()){
                Toast.makeText(requireContext(), "Introduce un correo electrónico", Toast.LENGTH_SHORT).show();
            } else if (!m.matches()){
                Toast.makeText(requireContext(), "Introduce un correo electrónico válido", Toast.LENGTH_SHORT).show();
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

                Map<String, Object> data = new HashMap<>();
                data.put("nombre", username);


                //Generacion User-login
                db.collection("users").document(auth.getCurrentUser().getUid()).set(new User("business"));

                //Generacion de la información de la peluqueria en la BD
                db.collection("peluquerias").document(auth.getUid()).set(data);

                //Horarios
                Map<String, Object> nullData = new HashMap<>();
                nullData.put("creation", null);

                db.collection("peluquerias").document(auth.getUid()).collection("horarios").document("lunes").set(nullData);
                db.collection("peluquerias").document(auth.getUid()).collection("horarios").document("martes").set(nullData);
                db.collection("peluquerias").document(auth.getUid()).collection("horarios").document("miércoles").set(nullData);
                db.collection("peluquerias").document(auth.getUid()).collection("horarios").document("jueves").set(nullData);
                db.collection("peluquerias").document(auth.getUid()).collection("horarios").document("viernes").set(nullData);
                db.collection("peluquerias").document(auth.getUid()).collection("horarios").document("sábado").set(nullData);
                db.collection("peluquerias").document(auth.getUid()).collection("horarios").document("domingo").set(nullData);


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