package com.example.apppeluquera;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.apppeluquera.databinding.FragmentPedirCitaBinding;
import com.example.apppeluquera.databinding.FragmentRegistrationBinding;

import java.util.Calendar;
import java.util.Timer;

public class PedirCitaFragment extends BaseFragment {

    private FragmentPedirCitaBinding binding;

    private Calendar c;
    private DatePickerDialog dpd;
    private TimePickerDialog tpd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentPedirCitaBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        c = Calendar.getInstance();


        binding.selectDay.setOnClickListener(v -> {

            int day = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH);
            int year = c.get(Calendar.YEAR);

            dpd = new DatePickerDialog(requireContext(),
                    (view1, year1, month1, day1) ->
                            binding.selectedDay.setText(day1 +"/"+(month1 +1)+"/"+ year1), year, month, day);
            dpd.show();
        });

        binding.selectHour.setOnClickListener(v -> {
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            tpd = new TimePickerDialog(requireContext(),
                    (view12, hourOfDay, minute1) ->
                            binding.selectedHour.setText(hourOfDay + ":" + minute), hour, minute,false);
            tpd.show();
        });

        binding.selectHairdresser.setOnClickListener(v -> {
            nav.navigate(R.id.action_pedirCitaFragment_to_seleccionPeluqueroFragment);
        });

    }
}