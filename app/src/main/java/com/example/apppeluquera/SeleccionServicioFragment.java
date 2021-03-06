package com.example.apppeluquera;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppeluquera.databinding.FragmentSeleccionServicioBinding;
import com.example.apppeluquera.databinding.ViewholderServicioBinding;
import com.example.apppeluquera.model.Servicio;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class SeleccionServicioFragment extends DialogFragment {

    private FragmentSeleccionServicioBinding binding;
    private NavController nav;
    List<Servicio> servicioList = new ArrayList<>();
    private AppViewModel appViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentSeleccionServicioBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ServiciosAdapter pa = new ServiciosAdapter();
        appViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        FirebaseFirestore.getInstance()
                .collection("peluquerias").document(appViewModel.peluqueriaMutableLiveData.getValue().getId())
                .collection("servicios").addSnapshotListener((snapshotServicios, error) -> {
                    servicioList.clear();
                    for (DocumentSnapshot snapshotServicio : snapshotServicios) {
                        servicioList.add(new Servicio(snapshotServicio.getId(), snapshotServicio.get("nombre").toString()));
                    }
                    pa.notifyDataSetChanged();
                    servicioList.forEach(p -> System.out.println(p.getNombre()));
                });

        binding.recyclerView.setAdapter(pa);
    }

    class ServiciosAdapter extends RecyclerView.Adapter<ServiciosAdapter.ServicioViewHolder>{


        @NonNull
        @Override
        public ServicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ServicioViewHolder(ViewholderServicioBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ServicioViewHolder holder, int position) {
            Servicio servicio = servicioList.get(position);

            holder.binding.name.setText(servicio.getNombre());

            holder.itemView.setOnClickListener(v -> {
                for (int i = 0; i < servicioList.size(); i++) {
                    if (servicioList.get(i).getNombre().equals(holder.binding.name.getText())) {
                        appViewModel.servicioMutableLiveData.setValue(servicioList.get(i));
                        break;
                    }
                }
                dismiss();
            });

        }

        @Override
        public int getItemCount() {
            return servicioList == null ? 0 : servicioList.size();
        }

        class ServicioViewHolder extends RecyclerView.ViewHolder{
            ViewholderServicioBinding binding;
            public ServicioViewHolder(@NonNull ViewholderServicioBinding binding) {
                super(binding.getRoot());

                this.binding = binding;
            }
        }
    }
}