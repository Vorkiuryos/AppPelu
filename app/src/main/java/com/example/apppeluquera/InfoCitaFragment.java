package com.example.apppeluquera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.apppeluquera.databinding.FragmentConsultDateBinding;
import com.example.apppeluquera.databinding.FragmentInfoCitaBinding;
import com.example.apppeluquera.model.Servicio;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class InfoCitaFragment extends BaseFragment {

    private FragmentInfoCitaBinding binding;
    private AppViewModel appViewModel;
    String telf = "";

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

            db.collection("peluquerias").document(appViewModel.citaMutableLiveData.getValue().getIdPeluqueria()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot snapshotPeluquerias, @Nullable FirebaseFirestoreException error) {
                    telf = snapshotPeluquerias.get("telefono").toString();
                }
            });
            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse(telf));
            if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)!=
                    PackageManager.PERMISSION_GRANTED)
                return;
            startActivity(i);

        });
        binding.canelarCita.setOnClickListener(v -> {
            //TODO Comprobación de si quieres borrar realmente la cita
            db.collection("peluquerias").document(appViewModel.citaMutableLiveData.getValue().getIdPeluqueria())
                    .collection("citas").document(appViewModel.citaMutableLiveData.getValue().getId()).delete();
            db.collection("users").document(auth.getUid()).collection("citas")
                    .document(appViewModel.citaMutableLiveData.getValue().getId()).delete();
            nav.navigate(R.id.consultDateFragment);

        });

    }
}