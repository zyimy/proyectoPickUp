package com.example.restaurant.pago;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.restaurant.NavigationDrawer;
import com.example.restaurant.R;

public class Pagos extends AppCompatActivity {

    private ImageView tarjeta,bizum,paypal;
    private Toolbar toolba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagos);

        tarjeta = findViewById(R.id.btnTarjetaPagoPrimero);
        bizum = findViewById(R.id.btnBizunPagoPrimero);
        paypal = findViewById(R.id.btnPaypalPagoPrimero);
        toolba = findViewById(R.id.toolbar);

        setSupportActionBar(toolba);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(true);
        ab.setTitle("Pago");


        tarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pagos.this,FragmentTarjeta.class);
                startActivity(intent);
            }
        });

        bizum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pagos.this,FragmentBizum.class);
                startActivity(intent);
            }
        });

        paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pagos.this,FragmentPaypal.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, NavigationDrawer.class);
        startActivity(intent);
        return false;
    }
}