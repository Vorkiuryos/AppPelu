package com.example.apppeluquera;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apppeluquera.databinding.FragmentGestionarHorariosBinding;
import com.example.apppeluquera.databinding.FragmentGestionarInfoBinding;
import com.example.apppeluquera.model.Peluqueria;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class GestionarInfoFragment extends BaseFragment {

    private FragmentGestionarInfoBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentGestionarInfoBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.editDireccion.setHint("Calle Ejemplo 123, Ciudad");
        binding.editNumTelf.setHint("623456789");
        binding.editDescripcion.setHint("Explica lo que quieras sobre tu negocio, da avisos o explica cosas.");

        DocumentReference docRef = db.collection("peluquerias").document(auth.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    try {
                        if (!document.getString("direccion").isEmpty()) {
                            binding.editDireccion.setText(document.getString("direccion"));
                        }
                        if (!document.getString("telefono").isEmpty()) {
                            binding.editNumTelf.setText(document.getString("telefono"));
                        }
                        if (!document.getString("descripcion").isEmpty()) {
                            binding.editDescripcion.setText(document.getString("descripcion"));
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
                db.collection("peluquerias").document(auth.getUid()).update("direccion", binding.editDireccion.getText().toString());
                db.collection("peluquerias").document(auth.getUid()).update("telefono", binding.editNumTelf.getText().toString());
                db.collection("peluquerias").document(auth.getUid()).update("descripcion", binding.editDescripcion.getText().toString());
                Toast.makeText(requireContext(), "Información actualizada", Toast.LENGTH_SHORT).show();
            }
        });

    }
}