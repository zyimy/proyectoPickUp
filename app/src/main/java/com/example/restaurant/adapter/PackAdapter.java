package com.example.restaurant.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restaurant.R;
import com.example.restaurant.model.Pack;
import com.example.restaurant.view.DescripcionPack;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PackAdapter extends RecyclerView.Adapter<PackAdapter.ViewHolder> {

    List<Pack> listaPack;
    Context context;


    public  PackAdapter(Context context,List<Pack>listaPack){
        this.context = context;
        this.listaPack = listaPack;
    }

    public void setListaPack(List<Pack>listaPack){
        this.listaPack = listaPack;
        notifyDataSetChanged();

    }


    @Override
    public PackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_packs,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PackAdapter.ViewHolder holder, int position) {

        String nom = listaPack.get(position).getNombre();
        holder.nombre.setText(nom);

        String direcci = listaPack.get(position).getDireccion();
        holder.direccion.setText(direcci);

        /*
        String descrip = listaPack.get(position).getDescripcion();
        holder.descripcion.setText(descrip);

         */

        String time = listaPack.get(position).getHora_disponible();
        holder.hora_disponible.setText(time);

        String dispon = listaPack.get(position).getStatus();
        holder.status.setText(dispon);

        double preci = listaPack.get(position).getPrecio();
        holder.precio.setText(String.valueOf(preci));

        Picasso.Builder builder = new  Picasso.Builder(context);
        builder.build().load(listaPack.get(position).getImagen())
                .placeholder(R.drawable.h)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imagen);

        holder.btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posicion = holder.getAdapterPosition();

                 Long id_pack = listaPack.get(posicion).getId_pack();
                 String nom = listaPack.get(posicion).getNombre();
                 String descri = listaPack.get(posicion).getDescripcion();
                 String direcc = listaPack.get(posicion).getDireccion();
                 double preci = listaPack.get(posicion).getPrecio();

                Intent intent = new Intent(context, DescripcionPack.class);
                intent.putExtra("id_pack",id_pack);
                intent.putExtra("nombre",nom);
                intent.putExtra("descripcion",descri);
                intent.putExtra("direccion",direcc);
                intent.putExtra("precio",preci);

                context.startActivity(intent);

            }
        });

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
        private TextView precio, descripcion, nombre,direccion,status,hora_disponible;
        private Button btnVer;
        public ViewHolder(@NonNull View v) {
            super(v);
            imagen = v.findViewById(R.id.imgImagen);
            precio = v.findViewById(R.id.lblPrecio);
            //descripcion = v.findViewById(R.id.lblDescripcion);
            nombre = v.findViewById(R.id.lblNombre);
            direccion = v.findViewById(R.id.lblDireccion);
            btnVer= v.findViewById(R.id.btnVerPack);
            status = v.findViewById(R.id.lblDisponible);
            hora_disponible = v.findViewById(R.id.lblHora);
        }
    }
}
