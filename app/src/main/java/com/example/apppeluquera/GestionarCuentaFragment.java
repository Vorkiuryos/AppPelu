package com.example.apppeluquera;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apppeluquera.databinding.FragmentGestionarCuentaBinding;
import com.example.apppeluquera.databinding.FragmentGestionarInfoBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;


public class GestionarCuentaFragment extends BaseFragment {
    private FragmentGestionarCuentaBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentGestionarCuentaBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                nav.navigate(R.id.action_gestionarCuentaFragment_to_menuFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        binding.editNumTelf.setHint("623456789");


        DocumentReference docRef = db.collection("users").document(auth.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    try {
                        binding.editNombre.setText(auth.getCurrentUser().getDisplayName());

                        if (!document.getString("telefono").isEmpty()) {
                            binding.editNumTelf.setText(document.getString("telefono"));
                        }

                    } catch (Exception e){
                    }

                } else {
                }
            }
        });

        binding.buttonEditInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.getCurrentUser().updateProfile(new UserProfileChangeRequest.Builder().setDisplayName(String.valueOf(binding.editNombre.getText())).build());
                db.collection("users").document(auth.getUid()).update("telefono", binding.editNumTelf.getText().toString());
                Toast.makeText(requireContext(), "Información actualizada", Toast.LENGTH_SHORT).show();
            }
        });

    }
}