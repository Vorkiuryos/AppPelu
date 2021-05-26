package com.example.apppeluquera;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppeluquera.databinding.FragmentConsultDateBinding;
import com.example.apppeluquera.databinding.ViewholderCitaBinding;
import com.example.apppeluquera.model.Cita;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class ConsultDateFragment extends BaseFragment {

    private FragmentConsultDateBinding binding;
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

        db.collection("users").document(auth.getUid())
                .collection("citas")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshotCitas, @Nullable FirebaseFirestoreException error) {
                citasList.clear();
                for(DocumentSnapshot snapshotCita : snapshotCitas){

                    Cita cita = new Cita(snapshotCita.get("fecha").toString(),
                            snapshotCita.getString("hora"),
                            snapshotCita.getString("id_peluqueria"),
                            snapshotCita.getString("id_servicio"),
                            snapshotCita.getString("id_usuario"),
                            snapshotCita.getString("nombre_peluqueria"),
                            snapshotCita.getString("nombre_servicio"),
                            snapshotCita.getString("nombre_cliente"));
                    cita.setId(snapshotCita.getId());
                    citasList.add(cita);

                }
                pa.notifyDataSetChanged();
            }
        });

        binding.recyclerView.setAdapter(pa);
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

            holder.binding.nombrePeluqueria.setText(cita.getNombrePeluqueria());
            holder.binding.diaHoraCita.setText(cita.getFecha() + " " + cita.getHora());

            holder.itemView.setOnClickListener(v -> {
                appViewModel.citaMutableLiveData.setValue(cita);
                nav.navigate(R.id.action_consultDateFragment_to_infoCitaFragment);
            });
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