package com.example.apppeluquera;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apppeluquera.databinding.FragmentSeleccionPeluqueroBinding;
import com.example.apppeluquera.databinding.ViewholderPeluqueroBinding;
import com.example.apppeluquera.model.Peluquero;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class SeleccionPeluqueroFragment extends DialogFragment {

    private FragmentSeleccionPeluqueroBinding binding;
    private NavController nav;
    List<Peluquero> peluqueroList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentSeleccionPeluqueroBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PeluquerosAdapter pa = new PeluquerosAdapter();

        FirebaseFirestore.getInstance()
                .collection("peluquerosprueba").document("1WWrhFe0dtQYvOUGmnHe")
                .collection("peluqueros").addSnapshotListener((snapshotPeluqueros, error) -> {
            peluqueroList.clear();
            for(DocumentSnapshot snapshotPeluquero: snapshotPeluqueros){
                peluqueroList.add(snapshotPeluquero.toObject(Peluquero.class));
            }
            // mostrar en la consola, solo para verlo
            peluqueroList.forEach(p -> System.out.println(p.getNombre()));
        });

        binding.recyclerView.setAdapter(pa);
    }

    class PeluquerosAdapter extends RecyclerView.Adapter<PeluquerosAdapter.PeluqueroViewHolder>{

        private PeluquerosAdapter.PeluqueroViewHolder binding2;

        @NonNull
        @Override
        public PeluqueroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PeluqueroViewHolder(ViewholderPeluqueroBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PeluqueroViewHolder holder, int position) {
            Peluquero peluquero = peluqueroList.get(position);

            holder.binding.name.setText(peluquero.getNombre());

            holder.itemView.setOnClickListener(v -> {

            });

        }

        @Override
        public int getItemCount() {
            return peluqueroList == null ? 0 : peluqueroList.size();
        }

        class PeluqueroViewHolder extends RecyclerView.ViewHolder{
            ViewholderPeluqueroBinding binding;
            public PeluqueroViewHolder(@NonNull ViewholderPeluqueroBinding binding) {
                super(binding.getRoot());

                this.binding = binding;
            }
        }
    }
}