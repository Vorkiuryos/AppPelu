package com.example.apppeluquera;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apppeluquera.databinding.FragmentAddHoraBinding;
import com.example.apppeluquera.databinding.FragmentSeleccionarHorasBinding;
import com.example.apppeluquera.databinding.ViewholderSeleccionHoraBinding;
import com.example.apppeluquera.model.Hora;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.jetbrains.annotations.NotNull;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class SeleccionarHorasFragment extends BaseFragment {

    private FragmentSeleccionarHorasBinding binding;
    private AppViewModel appViewModel;
    List<Hora> horas = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentSeleccionarHorasBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       SeleccionarHorasAdapter sa = new SeleccionarHorasAdapter();
       appViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        for (int i = 0; i < 3; i++) {
            horas.add(new Hora("0"+(i+7)+":00"));
        }
        for (int i = 0; i < 11; i++) {
            horas.add(new Hora((i+10)+":00"));
        }

        try {
            for (int i = 0; i < horas.size(); i++) {
                for (int j = 0; j < appViewModel.horasDiaSeleccionado.getValue().length; j++) {
                        if (horas.get(i).getHora().equals(appViewModel.horasDiaSeleccionado.getValue()[j])) {
                            horas.remove(i);
                        }
                }
            }
        } catch (Exception e) {
        }


       binding.recyclerView.setAdapter(sa);
    }

    private class SeleccionarHorasAdapter extends RecyclerView.Adapter<SeleccionarHorasViewHolder>{

        @NonNull
        @Override
        public SeleccionarHorasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SeleccionarHorasViewHolder(ViewholderSeleccionHoraBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull SeleccionarHorasViewHolder holder, int position) {
            Hora hora = horas.get(position);

            holder.binding.hora.setText(hora.getHora());

            holder.itemView.setOnClickListener(v -> {
                for (int i = 0; i < horas.size(); i++) {
                    if (horas.get(i).getHora().equals(holder.binding.hora.getText())) {
                        appViewModel.horaMutableLiveData.setValue(horas.get(i));

                        db.collection("peluquerias").document(auth.getUid())
                                .collection("horarios").document(appViewModel.diaHorarioString.getValue())
                                .update("horas", FieldValue.arrayUnion(appViewModel.horaMutableLiveData.getValue().getHora()));

                        nav.navigate(R.id.addHoraFragment);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return horas == null ? 0 : horas.size();
        }
    }

    class SeleccionarHorasViewHolder extends RecyclerView.ViewHolder{
        ViewholderSeleccionHoraBinding binding;
        public SeleccionarHorasViewHolder(@NonNull @NotNull ViewholderSeleccionHoraBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}