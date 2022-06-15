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

import com.example.restaurant.R;

import java.util.Timer;
import java.util.TimerTask;


public class FragmentBizum extends AppCompatActivity {

    private Button btnBizum;
    private ProgressBar pbBizum;
    private TextView mensaje;
    private Toolbar toolb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bizum);

        btnBizum = findViewById(R.id.btnPagarBizum);
        pbBizum = findViewById(R.id.pbBizum);
        mensaje = findViewById(R.id.lblMensajeBizum);
        toolb = findViewById(R.id.toolbar);

        setSupportActionBar(toolb);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(true);
        ab.setTitle("Pago Bizum");


        Intent intent = new Intent(this,FragmentPagoTotal.class);
        btnBizum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbBizum.setVisibility(View.VISIBLE);

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
        //onBackPressed();
        Intent intent = new Intent(this,Pagos.class);
        startActivity(intent);
        return false;
    }

}