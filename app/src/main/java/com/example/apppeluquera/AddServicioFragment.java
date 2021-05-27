package com.example.apppeluquera;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apppeluquera.databinding.FragmentAddServicioBinding;
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

public class AddServicioFragment extends BaseFragment {
    private FragmentAddServicioBinding binding;
    List<Servicio> servicios = new ArrayList<>();
    private AppViewModel appViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentAddServicioBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                nav.navigate(R.id.action_addServicioFragment_to_menuNegocioFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        AddServiciosAdapter pa = new AddServiciosAdapter();
        appViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        FirebaseFirestore.getInstance()
                .collection("peluquerias").document(auth.getUid())
                .collection("servicios").addSnapshotListener((snapshotServicios, error) -> {
                    servicios.clear();
                    for (DocumentSnapshot snapshotServicio : snapshotServicios) {
                        servicios.add(new Servicio(snapshotServicio.getId(), snapshotServicio.get("nombre").toString()));
                    }
                    pa.notifyDataSetChanged();
                    //servicios.forEach(p -> System.out.println(p.getNombre()));
                });

        binding.recyclerView.setAdapter(pa);

        binding.buttonNuevoServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav.navigate(R.id.action_addServicioFragment_to_addServicio2Fragment);
            }
        });

    }

    class AddServiciosAdapter extends RecyclerView.Adapter<AddServicioViewHolder>{

        @NonNull
        @Override
        public AddServicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AddServicioViewHolder(ViewholderServicioBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AddServicioViewHolder holder, int position) {
            Servicio servicio = servicios.get(position);

            holder.binding.name.setText(servicio.getNombre());

            holder.itemView.setOnClickListener(v -> {

                appViewModel.servicioMutableLiveData.setValue(servicio);
                nav.navigate(R.id.action_addServicioFragment_to_eliminarServicioFragment);

            });

        }

        @Override
        public int getItemCount() {
            return servicios == null ? 0 : servicios.size();
        }
    }

    class AddServicioViewHolder extends RecyclerView.ViewHolder{
        ViewholderServicioBinding binding;
        public AddServicioViewHolder(@NonNull ViewholderServicioBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
}