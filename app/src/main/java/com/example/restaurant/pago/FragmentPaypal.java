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


public class FragmentPaypal extends AppCompatActivity {

    private Button btnPaypal;
    private ProgressBar pbPaypal;
    private TextView mensaje;
    private Toolbar toolb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_paypal);

        btnPaypal = findViewById(R.id.btnPagarPaypal);
        pbPaypal = findViewById(R.id.pbPaiPal);
        mensaje = findViewById(R.id.lblMensajePayPal);
        toolb = findViewById(R.id.toolbar);

        setSupportActionBar(toolb);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(true);
        ab.setTitle("Pago Paypal");

        Intent intent = new Intent(this,FragmentPagoTotal.class);
        btnPaypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbPaypal.setVisibility(View.VISIBLE);

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