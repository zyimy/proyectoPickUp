package com.example.restaurant.view;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restaurant.MenuPrincipal;
import com.example.restaurant.NavigationDrawer;
import com.example.restaurant.R;
import com.example.restaurant.adapter.PackAdapter;
import com.example.restaurant.adapter.RestauranteAdapter;
import com.example.restaurant.model.Pack;
import com.example.restaurant.model.Restaurante;
import com.example.restaurant.model.RestaurantePack;
import com.example.restaurant.viewmodel.PackViewModel;
import com.example.restaurant.viewmodel.RestauranteViewModel;

import java.util.ArrayList;
import java.util.List;

public class Opcion extends AppCompatActivity {

    private RestauranteViewModel restauranteViewModel;
    private List<Restaurante>listarestaurante= new ArrayList<>();
    private List<Restaurante>listaPack= new ArrayList<>();
    private RecyclerView rv;
    private String nombreResta =" ";
    private PackViewModel packViewModel;
    private RestauranteAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcion);




    }

    public  void iniciar(){
        rv = findViewById(R.id.rvPrueba);
        linearLayoutManager = new LinearLayoutManager(Opcion.this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setHasFixedSize(true);
        adapter= new RestauranteAdapter(Opcion.this,listarestaurante);
        rv.setAdapter(adapter);
    }

    public void verId(){
        Bundle bundle = getIntent().getExtras();

        Long idPack = bundle.getLong("id_pack");

    }


    public void opcionRestaurador(View view){
        Intent intent = new Intent(Opcion.this, NavigationDrawer.class);
        startActivity(intent);
    }

    public void configurarModel(){
        restauranteViewModel = new ViewModelProvider(this).get(RestauranteViewModel.class);
        restauranteViewModel.init();
        restauranteViewModel.getObserverListaRestaurante().observe(this, new Observer<List<Restaurante>>() {
            @Override
            public void onChanged(List<Restaurante> pack) {
                if (pack !=null){
                    listarestaurante = pack;
                    adapter.setListaPack(listarestaurante);
                    adapter.notifyDataSetChanged();

                    for (Restaurante re :listarestaurante){
                        if (!re.getPack().isEmpty()){
                            nombreResta = nombreResta + re.getNombre_restaurante()+" , ";
                        }
                    }

                }
            }
        });

    }
}