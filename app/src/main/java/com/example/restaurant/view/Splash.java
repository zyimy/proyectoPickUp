package com.example.restaurant.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.restaurant.NavigationDrawer;
import com.example.restaurant.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferences = this.getSharedPreferences("sesiones",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //String email = sharedPreferences.getString("contrasena",null);
        boolean inicioSesion = sharedPreferences.getBoolean("sesion",false);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (inicioSesion){
                    Intent intent = new Intent(Splash.this,NavigationDrawer.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(Splash.this,Opcion.class);
                    startActivity(intent);
                }
                goTo();


            }
        };

        Timer timer = new Timer();

        timer.schedule(timerTask,5000);

    }

    public void   goTo(){
        if (firebaseAuth.getCurrentUser()!= null){
            Intent intent = new Intent(Splash.this, NavigationDrawer.class);
            startActivity(intent);

        }else{
            Intent intent = new Intent(Splash.this, Login.class);
            startActivity(intent);
        }
    }

    public void preferenciaUsuario(){
        SharedPreferences preferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        String email = preferences.getString("email","llave");
        String password = preferences.getString("password","llave");

        if(!email.equals("llave")){
            Intent intent = new Intent(Splash.this, NavigationDrawer.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(Splash.this,Opcion.class);
            startActivity(intent);
        }
    }

}