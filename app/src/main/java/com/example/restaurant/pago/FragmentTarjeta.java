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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.restaurant.NavigationDrawer;
import com.example.restaurant.R;
import com.example.restaurant.view.Opcion;
import com.example.restaurant.view.Splash;

import java.util.Timer;
import java.util.TimerTask;


public class FragmentTarjeta extends AppCompatActivity {

    private Button pagoTarjeta;
    private ProgressBar pbTarjet;
    private TextView mensaje;
    private Toolbar toolba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tarjeta);

        pagoTarjeta = findViewById(R.id.btnPagarTarjeta);
        pbTarjet = findViewById(R.id.pbTarjeta);
        mensaje = findViewById(R.id.lblMensajeTarjeta);
        toolba = findViewById(R.id.toolbar);

        setSupportActionBar(toolba);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(true);
        ab.setTitle("Pago Tarjeta");

        Intent intent = new Intent(this,FragmentPagoTotal.class);
        pagoTarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbTarjet.setVisibility(View.VISIBLE);

                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {

                        startActivity(intent);

                    }
                };

                Timer timer = new Timer();

                timer.schedule(timerTask,5000);

                mensaje.setText("Su pago se esta procesando espere un momento");
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this,Pagos.class);
        startActivity(intent);
        return false;
    }
}