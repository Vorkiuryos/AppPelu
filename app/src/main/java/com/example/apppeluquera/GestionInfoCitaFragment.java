package com.example.apppeluquera;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apppeluquera.databinding.FragmentGestionInfoCitaBinding;
import com.example.apppeluquera.databinding.FragmentInfoCitaBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;


public class GestionInfoCitaFragment extends BaseFragment {

    private FragmentGestionInfoCitaBinding binding;
    private AppViewModel appViewModel;
    String telf = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentGestionInfoCitaBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                nav.navigate(R.id.action_gestionInfoCitaFragment_to_gestionarCitaFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        appViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        binding.nombreCliente.setText(appViewModel.citaMutableLiveData.getValue().getNombreCliente());
        binding.diaCita.setText(appViewModel.citaMutableLiveData.getValue().getFecha());
        binding.horaCita.setText(appViewModel.citaMutableLiveData.getValue().getHora());
        binding.serviciosCita.setText(appViewModel.citaMutableLiveData.getValue().getNombreServicio());

        binding.canelarCita.setOnClickListener(v -> {
            //TODO Comprobación de si quieres borrar realmente la cita
            db.collection("peluquerias").document(appViewModel.citaMutableLiveData.getValue().getIdPeluqueria()).collection("citas").document(appViewModel.citaMutableLiveData.getValue().getId()).delete();
            db.collection("users").document(appViewModel.citaMutableLiveData.getValue().getIdUsuario()).collection("citas").document(appViewModel.citaMutableLiveData.getValue().getId()).delete();
            nav.navigate(R.id.gestionarCitaFragment);

        });

        binding.buttonLlamarCliente.setOnClickListener(v -> {
            DocumentReference docRef = db.collection("users").document(appViewModel.citaMutableLiveData.getValue().getIdUsuario());
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
                                Toast.makeText(requireContext(), "Este usuario no tiene numero de contacto", Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e){
                            Toast.makeText(requireContext(), "Este usuario no tiene numero de contacto", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                    }
                }
            });

        });


    }
}