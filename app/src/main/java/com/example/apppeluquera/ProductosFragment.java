package com.example.apppeluquera;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.apppeluquera.databinding.FragmentProductosBinding;
import com.example.apppeluquera.databinding.ViewholderProductoBinding;
import com.example.apppeluquera.model.Producto;

import java.util.List;

public class ProductosFragment extends Fragment {

    FragmentProductosBinding binding;
    ProductosViewModel pvm;
    NavController nav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentProductosBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(requireContext(), "Expositor de prueba", Toast.LENGTH_SHORT).show();
        pvm = new ViewModelProvider(this).get(ProductosViewModel.class);
        nav= Navigation.findNavController(view);

        ProductosAdapter pa = new ProductosAdapter();
        binding.recyclerView.setAdapter(pa);

        pvm.productos().observe(getViewLifecycleOwner(), new Observer<List<Producto>>() {
            @Override
            public void onChanged(List<Producto> productos) {
                pa.setProductoList(productos);
            }
        });

    }

    class ProductosAdapter extends RecyclerView.Adapter<ProductosViewHolder>{

        List<Producto> productoList;

        @NonNull
        @Override
        public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ProductosViewHolder(ViewholderProductoBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ProductosViewHolder holder, int position) {
            Producto producto = productoList.get(position);

            holder.binding.nombre.setText(producto.getNombre());
            holder.binding.descripcion.setText(producto.getDescripcion());
            Glide.with(holder.itemView).load(producto.getImagen()).into(holder.binding.iamgen);
        }

        @Override
        public int getItemCount() {
            return productoList == null ? 0 : productoList.size();
        }

        public void setProductoList(List<Producto> productoList){
            this.productoList = productoList;
            notifyDataSetChanged();
        }
    }

    private class ProductosViewHolder extends RecyclerView.ViewHolder {
        ViewholderProductoBinding binding;
        public ProductosViewHolder(@NonNull ViewholderProductoBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
}