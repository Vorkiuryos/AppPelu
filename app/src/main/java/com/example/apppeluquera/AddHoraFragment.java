package com.example.apppeluquera;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apppeluquera.databinding.FragmentAddHoraBinding;
import com.example.apppeluquera.databinding.FragmentSeleccionPeluqueriaBinding;
import com.example.apppeluquera.databinding.ViewholderSeleccionHoraBinding;
import com.example.apppeluquera.databinding.ViewholderSeleccionPeluqueriaBinding;
import com.example.apppeluquera.model.Hora;
import com.example.apppeluquera.model.Peluqueria;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class AddHoraFragment extends BaseFragment {

    private FragmentAddHoraBinding binding;
    private AppViewModel appViewModel;
    List<Hora> horas = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentAddHoraBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                nav.navigate(R.id.action_addHoraFragment_to_horarioSemanalFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);


        AddHoraAdapter ha = new AddHoraAdapter();
        appViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        String diaSemana = "";

        FirebaseFirestore.getInstance()
                .collection("peluquerias").document(auth.getUid())
                .collection("horarios").document(appViewModel.diaHorarioString.getValue()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshotHorarios, @Nullable FirebaseFirestoreException error) {
                horas.clear();
                try {
                    String recibePlano = snapshotHorarios.get("horas").toString();
                    recibePlano = recibePlano.substring(1, recibePlano.length() - 1);
                    String[] arrayHoras = recibePlano.split(", ");

                    //ORDENAR EL STRING ARRAY DE HORAS (PARA SE LE MUESTRE ORDENADO AL USUARIO)
                    for (int i = 0; i < arrayHoras.length; i++) {
                        for (int j = i + 1; j < arrayHoras.length; j++) {
                            String tmp = "";
                            if (Integer.parseInt(arrayHoras[i].substring(0,2)) > Integer.parseInt(arrayHoras[j].substring(0,2))) {
                                tmp = arrayHoras[i];
                                arrayHoras[i] = arrayHoras[j];
                                arrayHoras[j] = tmp;
                            }
                        }
                    }

                    appViewModel.horasDiaSeleccionado.setValue(arrayHoras);

                    for (int i = 0; i < arrayHoras.length; i++) {
                        if (!arrayHoras[i].isEmpty()) {
                            horas.add(new Hora(arrayHoras[i]));
                        }
                    }
                    ha.notifyDataSetChanged();
                } catch (Exception e) {
                }


            }
        });

        binding.recyclerView.setAdapter(ha);


        binding.buttonNuevaHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav.navigate(R.id.action_addHoraFragment_to_seleccionarHorasFragment);
            }
        });
    }

    private class AddHoraAdapter extends RecyclerView.Adapter<AddHoraViewHolder>{


        @NonNull
        @Override
        public AddHoraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AddHoraViewHolder(ViewholderSeleccionHoraBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AddHoraViewHolder holder, int position) {
            Hora hora = horas.get(position);

            holder.binding.hora.setText(hora.getHora());

            holder.itemView.setOnClickListener(v -> {

                appViewModel.horaMutableLiveData.setValue(hora);
                nav.navigate(R.id.eliminarHoraFragment);

            });
        }

        @Override
        public int getItemCount() {
            return horas == null ? 0 : horas.size();
        }
    }

    class AddHoraViewHolder extends RecyclerView.ViewHolder{
        ViewholderSeleccionHoraBinding binding;
        public AddHoraViewHolder(@NonNull @NotNull ViewholderSeleccionHoraBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}