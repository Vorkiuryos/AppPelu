package com.example.apppeluquera;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apppeluquera.databinding.FragmentMenuBinding;
import com.example.apppeluquera.databinding.FragmentMenuNegocioBinding;


public class MenuNegocioFragment extends BaseFragment {

    private FragmentMenuNegocioBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMenuNegocioBinding.inflate(inflater, container, false)).getRoot();
    }
}