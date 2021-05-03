package com.example.apppeluquera;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
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
                            binding.selectedDay.setText(day1 + "/" + (month1 + 1) + "/" + year1), year, month, day);
            dpd.show();

        });

        binding.selectedDay.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                binding.selectHour.setImageResource(R.drawable.hour2);
                binding.selectedHour.setEnabled(true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

        });

        binding.selectHour.setOnClickListener(v -> {

            if(binding.selectedHour.isEnabled()){
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                tpd = new TimePickerDialog(requireContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view12, int hourOfDay, int minute1) {
                                binding.selectedHour.setText(String.format("%02d:%02d", hourOfDay, minute1));
                            }
                        }, hour, minute,false);
                tpd.show();
            }

        });

        binding.selectedHour.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                binding.selectHairdresser.setImageResource(R.drawable.hairdersser_icon2);
                binding.selectedHairdresser.setEnabled(true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

        });

        binding.selectHairdresser.setOnClickListener(v -> {

            if(binding.selectedHour.isEnabled()){
                nav.navigate(R.id.action_pedirCitaFragment_to_seleccionPeluqueroFragment);
                binding.selectedHairdresser.setText("Peluquero seleccionado");
            }

        });

        binding.selectedHairdresser.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                binding.appointmentButton.setEnabled(true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

        });


        binding.appointmentButton.setOnClickListener(v -> {

        });


    }
}