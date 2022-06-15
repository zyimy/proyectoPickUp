package com.example.restaurant.pago;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.restaurant.NavigationDrawer;
import com.example.restaurant.R;


public class FragmentPagoTotal extends AppCompatActivity {

    private Toolbar toolba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pago_total);
        toolba = findViewById(R.id.toolbar);

        setSupportActionBar(toolba);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(true);
        ab.setTitle("Pago Total");


    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, NavigationDrawer.class);
        startActivity(intent);
        return false;
    }
}