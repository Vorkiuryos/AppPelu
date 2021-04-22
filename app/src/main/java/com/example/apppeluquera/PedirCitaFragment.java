package com.example.apppeluquera;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.apppeluquera.databinding.FragmentPedirCitaBinding;
import com.example.apppeluquera.databinding.FragmentRegistrationBinding;

import java.util.Calendar;

public class PedirCitaFragment extends Fragment {

    private FragmentPedirCitaBinding binding;

    private Calendar c;
    private DatePickerDialog dpd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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

            dpd = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    binding.selectedDay.setText(day+"/"+(month+1)+"/"+year);
                }
            }, year, month, day);
            dpd.show();
        });

    }
}