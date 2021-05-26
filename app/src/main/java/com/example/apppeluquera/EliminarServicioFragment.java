package com.example.apppeluquera;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apppeluquera.databinding.FragmentEliminarHoraBinding;
import com.example.apppeluquera.databinding.FragmentEliminarServicioBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class EliminarServicioFragment extends Fragment {

    private FragmentEliminarServicioBinding binding;
    private AppViewModel appViewModel;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    NavController nav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentEliminarServicioBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        appViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);
        db =  FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        nav = Navigation.findNavController(view);


        binding.buttonRemoveService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("peluquerias").document(auth.getUid()).collection("servicios").document(appViewModel.servicioMutableLiveData.getValue().getId()).delete();
                nav.navigate(R.id.addServicioFragment);
            }
        });

    }



}