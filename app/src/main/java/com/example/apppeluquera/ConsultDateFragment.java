package com.example.apppeluquera;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppeluquera.databinding.FragmentConsultDateBinding;
import com.example.apppeluquera.databinding.ViewholderCitaBinding;
import com.example.apppeluquera.model.Cita;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class ConsultDateFragment extends Fragment {

    private FragmentConsultDateBinding binding;
    private NavController nav;
    private AppViewModel appViewModel;
    List<Cita> citasList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentConsultDateBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CitasAdapter pa = new CitasAdapter();
        appViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        FirebaseFirestore.getInstance()
                .collection("peluquerias").document("5FBzk6ANkRsIzVZ4R6b0")
                .collection("servicios").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshotPeluqueros, @Nullable FirebaseFirestoreException error) {
                citasList.clear();
                for (DocumentSnapshot snapshotPeluquero : snapshotPeluqueros) {
                    citasList.add(snapshotPeluquero.toObject(Cita.class));
                }
                // mostrar en la consola, solo para verlo
                //citasList.forEach(cita -> System.out.println(cita.getNombre()));
            }
        });
}

    private class CitasAdapter extends RecyclerView.Adapter<CitaViewHolder> {

        @NonNull
        @Override
        public CitaViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            return new CitaViewHolder(ViewholderCitaBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull CitaViewHolder holder, int position) {
            Cita cita = citasList.get(position);

        }

        @Override
        public int getItemCount() {
            return citasList == null ? 0 : citasList.size();
        }
    }

    private class CitaViewHolder extends RecyclerView.ViewHolder{
        ViewholderCitaBinding binding;
        public CitaViewHolder(@NonNull @NotNull ViewholderCitaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}