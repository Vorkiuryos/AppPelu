package com.example.apppeluquera;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.apppeluquera.databinding.FragmentPedirCitaBinding;

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

        appViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        binding.selectDay.setOnClickListener(v -> {
            nav.navigate(R.id.action_pedirCitaFragment_to_seleccionFechaFragment);
        });

       appViewModel.fechaMutableLiveData.observe(getViewLifecycleOwner(), fecha -> {
           if(fecha != null){
               binding.selectedDay.setText(fecha.toString());
               appViewModel.fechaStringPreview.setValue(fecha.toString());

               binding.selectHour.setImageResource(R.drawable.hour2);
               binding.selectedHour.setEnabled(true);
           }
       });

        binding.selectHour.setOnClickListener(v -> {
            nav.navigate(R.id.action_pedirCitaFragment_to_seleccionHoraFragment);
        });

        appViewModel.horaMutableLiveData.observe(getViewLifecycleOwner(), hora -> {
            if(hora != null){
                binding.selectedHour.setText(hora.getHora());
                binding.selectHairdresser.setImageResource(R.drawable.hairdersser_icon2);
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
            String hour = appViewModel.horaMutableLiveData.getValue().getHora().substring(0,2) + ":"
                    + appViewModel.horaMutableLiveData.getValue().getHora().substring(2,2);

            //Date dateSend = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date);

            Map<String, Object> data = new HashMap<>();
            data.put("id_peluqueria", appViewModel.peluqueriaMutableLiveData.getValue().getId());
            data.put("nombre_peluqueria", appViewModel.peluqueriaMutableLiveData.getValue().getNombre());
            data.put("id_servicio", appViewModel.servicioMutableLiveData.getValue().getId());
            data.put("nombre_servicio", appViewModel.servicioMutableLiveData.getValue().getNombre());
            data.put("id_usuario", auth.getCurrentUser().getUid());
            data.put("fecha", date);
            data.put("hora", hour);

            db.collection("users").document(auth.getUid()).collection("citas").add(data);


            try {
                getParentFragmentManager().beginTransaction().remove(this).commit();
                nav.navigate(R.id.menuFragment);
            } catch (Throwable e) {

            }

        });

    }
}