package com.example.restaurant.view;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.restaurant.R;

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