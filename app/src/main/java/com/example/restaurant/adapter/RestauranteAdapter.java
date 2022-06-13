package com.example.restaurant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restaurant.R;
import com.example.restaurant.model.Restaurante;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RestauranteAdapter extends RecyclerView.Adapter<RestauranteAdapter.ViewHolder>{

    List<Restaurante> listaPack;
    Context context;


    public  RestauranteAdapter(Context context,List<Restaurante>listaPack){
        this.context = context;
        this.listaPack = listaPack;
    }

    public void setListaPack(List<Restaurante>listaPack){
        this.listaPack = listaPack;
        notifyDataSetChanged();

    }

    @NonNull
    @NotNull
    @Override
    public RestauranteAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_packs,parent,false);
        RestauranteAdapter.ViewHolder viewHolder = new RestauranteAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RestauranteAdapter.ViewHolder holder, int position) {

        String nom = listaPack.get(position).getNombre();
        holder.nombre.setText(nom);

        String direcci = listaPack.get(position).getDireccion();
        holder.direccion.setText(direcci);

        String descrip = listaPack.get(position).getDescripcion();
        holder.descripcion.setText(descrip);

        double preci = listaPack.get(position).getPrecio();
        holder.precio.setText(String.valueOf(preci));

        Picasso.Builder builder = new  Picasso.Builder(context);
        builder.build().load(listaPack.get(position).getImagen())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imagen);


    }

    @Override
    public int getItemCount() {
        if (this.listaPack !=null){
            return listaPack.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagen;
        private TextView precio, descripcion, nombre,direccion,nombre_restaurant;
        public ViewHolder(@NonNull @NotNull View v) {
            super(v);

            imagen = v.findViewById(R.id.imgImagen);
            precio = v.findViewById(R.id.lblPrecio);
            //descripcion = v.findViewById(R.id.lblDescripcion);
            nombre = v.findViewById(R.id.lblNombre);
            direccion = v.findViewById(R.id.lblDireccion);

        }
    }
}
