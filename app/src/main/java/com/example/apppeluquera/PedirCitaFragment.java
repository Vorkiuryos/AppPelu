package com.example.apppeluquera;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.apppeluquera.databinding.FragmentPedirCitaBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PedirCitaFragment extends BaseFragment {

    private FragmentPedirCitaBinding binding;
    private AppViewModel appViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentPedirCitaBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                nav.navigate(R.id.action_pedirCitaFragment_to_seleccionPeluqueriaFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        appViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        DocumentReference docRef = db.collection("peluquerias").document(appViewModel.peluqueriaMutableLiveData.getValue().getId());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    try {
                        if (!document.getString("nombre").isEmpty()) {
                            binding.nombrePeluqueria.setText(document.getString("nombre"));
                        }
                        if (!document.getString("descripcion").isEmpty()) {
                            binding.citaDescripcion.setText(document.getString("descripcion"));
                        }

                    } catch (Exception e){
                    }

                } else {
                }
            }
        });

        binding.selectDay.setOnClickListener(v -> {
            nav.navigate(R.id.action_pedirCitaFragment_to_seleccionFechaFragment);
        });

       appViewModel.fechaMutableLiveData.observe(getViewLifecycleOwner(), fecha -> {
           if(fecha != null){
               binding.selectedDay.setText(fecha.toString());
               appViewModel.fechaStringPreview.setValue(fecha.toString());

               binding.imageSelectHour.setImageResource(R.drawable.hour2);
               binding.selectedHour.setEnabled(true);
           }
       });

        binding.selectHour.setOnClickListener(v -> {
            nav.navigate(R.id.action_pedirCitaFragment_to_seleccionHoraFragment);
        });

        appViewModel.horaMutableLiveData.observe(getViewLifecycleOwner(), hora -> {
            if(hora != null){
                binding.selectedHour.setText(hora.getHora());
                binding.imageSelectService.setImageResource(R.drawable.hairdersser_icon2);
                binding.selectedHairdresser.setEnabled(true);
            }
        });

        binding.selectHairdresser.setOnClickListener(v -> {
            System.out.println("jsjsjs");
            nav.navigate(R.id.action_pedirCitaFragment_to_seleccionServicioFragment);
        });

        appViewModel.servicioMutableLiveData.observe(getViewLifecycleOwner(), servicio -> {
            if (servicio != null) {
                binding.selectedHairdresser.setText(appViewModel.servicioMutableLiveData.getValue().getNombre());
                binding.appointmentButton.setEnabled(true);
            }
        });




        binding.appointmentButton.setOnClickListener(v -> {

            String date = appViewModel.fechaMutableLiveData.getValue().getDay() + "/"
                    + appViewModel.fechaMutableLiveData.getValue().getMonth() + "/"
                    + appViewModel.fechaMutableLiveData.getValue().getYear();
            String hour = appViewModel.horaMutableLiveData.getValue().getHora();

            Map<String, Object> data = new HashMap<>();
            data.put("id_peluqueria", appViewModel.peluqueriaMutableLiveData.getValue().getId());
            data.put("nombre_peluqueria", appViewModel.peluqueriaMutableLiveData.getValue().getNombre());
            data.put("id_servicio", appViewModel.servicioMutableLiveData.getValue().getId());
            data.put("nombre_servicio", appViewModel.servicioMutableLiveData.getValue().getNombre());
            data.put("id_usuario", auth.getCurrentUser().getUid());
            data.put("fecha", date);
            data.put("hora", hour);
            data.put("nombre_cliente", auth.getCurrentUser().getDisplayName());


            db.collection("users").document(auth.getUid()).collection("citas").add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    String citaId = documentReference.getId();
                    db.collection("peluquerias").document(appViewModel.peluqueriaMutableLiveData.getValue().getId()).collection("citas").document(citaId).set(data);
                }
            });

            try {
                getParentFragmentManager().beginTransaction().remove(this).commit();
                nav.navigate(R.id.menuFragment);
            } catch (Throwable e) {

            }

        });

    }
}