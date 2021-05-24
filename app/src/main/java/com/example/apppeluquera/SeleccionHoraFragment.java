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

import com.example.apppeluquera.databinding.FragmentSeleccionHoraBinding;
import com.example.apppeluquera.databinding.ViewholderSeleccionHoraBinding;
import com.example.apppeluquera.databinding.ViewholderServicioBinding;
import com.example.apppeluquera.model.Hora;
import com.example.apppeluquera.model.Servicio;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class SeleccionHoraFragment extends DialogFragment {

    private FragmentSeleccionHoraBinding binding;
    private NavController nav;
    List<Hora> horas = new ArrayList<>();
    private AppViewModel appViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentSeleccionHoraBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SelecionHoraAdapter sa = new SelecionHoraAdapter();
        appViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        //FirebaseFirestore.getInstance()


        binding.recyclerView.setAdapter(sa);
    }

    private class SelecionHoraAdapter extends RecyclerView.Adapter<SelecionHoraAdapter.SeleccionHoraViewHolder>{

        @NonNull
        @Override
        public SeleccionHoraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           return new SeleccionHoraViewHolder(ViewholderSeleccionHoraBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull SeleccionHoraViewHolder holder, int position) {
            Hora hora = horas.get(position);

            holder.binding.hora.setText(hora.getHora());

            holder.itemView.setOnClickListener(v -> {
                for (int i = 0; i < horas.size(); i++) {
                    if (horas.get(i).getHora().equals(holder.binding.hora.getText())) {
                        appViewModel.horaMutableLiveData.setValue(horas.get(i));
                        break;
                    }
                }
                dismiss();
            });
        }

        @Override
        public int getItemCount() {
            return horas == null ? 0 : horas.size();
        }

        class SeleccionHoraViewHolder extends RecyclerView.ViewHolder{
            ViewholderSeleccionHoraBinding binding;
            public SeleccionHoraViewHolder(@NonNull @NotNull ViewholderSeleccionHoraBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }
    }

}