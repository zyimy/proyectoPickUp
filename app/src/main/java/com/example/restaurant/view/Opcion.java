package com.example.restaurant.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;
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

    private TextView restaurador,usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcion);

    }

    //Accion al dar click nos lleva al activity loginRestaurante
    public void clickRestaurador(View view){
        Intent intent = new Intent(Opcion.this,LoginRestaurante.class);
        startActivity(intent);
    }

    //Accion al dar click nos lleva al activity login
    public void clickUser(View view){
        Intent intent = new Intent(Opcion.this,Login.class);
        startActivity(intent);
    }




}