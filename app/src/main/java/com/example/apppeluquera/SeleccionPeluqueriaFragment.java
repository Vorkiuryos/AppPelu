package com.example.apppeluquera;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apppeluquera.databinding.FragmentConsultDateBinding;
import com.example.apppeluquera.databinding.FragmentSeleccionPeluqueriaBinding;
import com.example.apppeluquera.databinding.ViewholderCitaBinding;
import com.example.apppeluquera.databinding.ViewholderSeleccionPeluqueriaBinding;
import com.example.apppeluquera.model.Cita;
import com.example.apppeluquera.model.Peluqueria;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SeleccionPeluqueriaFragment extends BaseFragment {

    private FragmentSeleccionPeluqueriaBinding binding;
    private AppViewModel appViewModel;
    List<Peluqueria> peluquerias = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentSeleccionPeluqueriaBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PeluqueriasAdapter pa = new PeluqueriasAdapter();
        appViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        db.collection("peluquerias")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshotPeluquerias, @Nullable FirebaseFirestoreException error) {
                        peluquerias.clear();
                        for(DocumentSnapshot snapshotPeluqueria : snapshotPeluquerias){

                            Peluqueria peluqueria = new Peluqueria(snapshotPeluqueria.getString("nombre"),
                                    (snapshotPeluqueria.getString("direccion")));
                            peluqueria.setId(snapshotPeluqueria.getId());

                            peluquerias.add(peluqueria);
                        }
                        pa.notifyDataSetChanged();
                    }
                });

        binding.recyclerView.setAdapter(pa);
    }

    private class PeluqueriasAdapter extends RecyclerView.Adapter<SeleccionPeluqueriaViewHolder>{

        @NonNull
        @Override
        public SeleccionPeluqueriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SeleccionPeluqueriaViewHolder(ViewholderSeleccionPeluqueriaBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull SeleccionPeluqueriaViewHolder holder, int position) {
            Peluqueria peluqueria = peluquerias.get(position);

            holder.binding.nombrePeluqueria.setText(peluqueria.getNombre());
            holder.binding.direccionPeluqueria.setText(peluqueria.getDireccion());

            holder.itemView.setOnClickListener(v -> {
                appViewModel.peluqueriaMutableLiveData.setValue(peluqueria);
                nav.navigate(R.id.pedirCitaFragment);
            });

        }

        @Override
        public int getItemCount() {
            return peluquerias == null ? 0 : peluquerias.size();
        }
    }

    class SeleccionPeluqueriaViewHolder extends RecyclerView.ViewHolder{
        ViewholderSeleccionPeluqueriaBinding binding;
        public SeleccionPeluqueriaViewHolder(@NonNull @NotNull ViewholderSeleccionPeluqueriaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}