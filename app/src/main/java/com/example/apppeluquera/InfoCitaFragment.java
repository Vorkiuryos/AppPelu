package com.example.apppeluquera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.apppeluquera.databinding.FragmentConsultDateBinding;
import com.example.apppeluquera.databinding.FragmentInfoCitaBinding;
import com.example.apppeluquera.model.Servicio;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class InfoCitaFragment extends BaseFragment {

    private FragmentInfoCitaBinding binding;
    private AppViewModel appViewModel;
    String telf;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentInfoCitaBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                nav.navigate(R.id.action_infoCitaFragment_to_consultDateFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        appViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        binding.nombrePeluqueria.setText(appViewModel.citaMutableLiveData.getValue().getNombrePeluqueria());
        binding.diaCita.setText(appViewModel.citaMutableLiveData.getValue().getFecha());
        binding.horaCita.setText(appViewModel.citaMutableLiveData.getValue().getHora());
        binding.serviciosCita.setText(appViewModel.citaMutableLiveData.getValue().getNombreServicio());



        binding.buttonLlamarPeluqueria.setOnClickListener(v -> {
            DocumentReference docRef = db.collection("peluquerias").document(appViewModel.citaMutableLiveData.getValue().getIdPeluqueria());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        try {
                            if (!document.getString("telefono").isEmpty() && !document.getString("telefono").equals("")) {
                                telf = document.getString("telefono");

                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+telf)));
                            } else {
                                Toast.makeText(requireContext(), "Este negocio no tiene numero de contacto", Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e){
                            Toast.makeText(requireContext(), "Este negocio no tiene numero de contacto", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                    }
                }
            });

        });

        binding.canelarCita.setOnClickListener(v -> {
            //TODO Comprobaci??n de si quieres borrar realmente la cita
            db.collection("peluquerias").document(appViewModel.citaMutableLiveData.getValue().getIdPeluqueria())
                    .collection("citas").document(appViewModel.citaMutableLiveData.getValue().getId()).delete();
            db.collection("users").document(auth.getUid()).collection("citas")
                    .document(appViewModel.citaMutableLiveData.getValue().getId()).delete();
            nav.navigate(R.id.consultDateFragment);

        });

    }
}