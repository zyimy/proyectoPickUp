 package com.example.restaurant.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import com.example.restaurant.NavigationDrawer;
import com.example.restaurant.R;
import com.example.restaurant.model.User;
import com.example.restaurant.network.ApiService;
import com.example.restaurant.network.RetroInstance;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class RegistroUser extends AppCompatActivity {
     private TextView nombre, email, contrasena;
     private Button ingresarUser;
     private ApiService apiService;
     private Toolbar toolbars;
     private SharedPreferences sharedPreferences;
     private SharedPreferences.Editor editor;
     private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_user);
        toolbars = findViewById(R.id.toolbar);
        ingresarUser = findViewById(R.id.btnIngresarUser);
        sharedPreferences = this.getSharedPreferences("usuarios", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        pb= findViewById(R.id.pbUser);

        setSupportActionBar(toolbars);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(true);
        ab.setTitle("Registro");

        pb.setVisibility(View.INVISIBLE);

         ingresarUser.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 TimerTask timerTask = new TimerTask() {
                     @Override
                     public void run() {

                         mostrarUser();
                     }
                 };
                 pb.setVisibility(View.VISIBLE);
                 Timer timer = new Timer();

                 timer.schedule(timerTask,2000);

             }
         });
    }


    //Volver atras
     @Override
     public boolean onSupportNavigateUp() {
         onBackPressed();
         return false;
     }

     //Crear usuario
    public User mostrarUser(){
        nombre = findViewById(R.id.txtNombreUser);
        email = findViewById(R.id.txtEmailUser);
        contrasena = findViewById(R.id.txtContrasenaUser);

        if (nombre.length() ==0 || email.length() == 0 || contrasena.length() == 0) {
            Toast.makeText(RegistroUser.this, "Debe rellenar todos los campos", Toast.LENGTH_LONG).show();
        }

        User user = new User(contrasena.getText().toString(),
                email.getText().toString(),
                nombre.getText().toString());

        if (user.getEmail().isEmpty() || user.getNombre().isEmpty() || user.getContrasena().isEmpty()){

        }else{
            responseApiUser(user);
            guardarSession(nombre.getText().toString(),email.getText().toString());
        }
        return user;
    }



    //Respuesta del service para guardar usuario en la BBDD
     public void responseApiUser(User user){
         apiService = RetroInstance.getRetrofit().create(ApiService.class);
         apiService.getIngresarUser(user).enqueue(new Callback<User>() {
             @Override
             public void onResponse(Call<User> call, Response<User> response) {
                 if (response.isSuccessful()){
                     Toast.makeText(RegistroUser.this,"Usuario Registrado correctamente",Toast.LENGTH_SHORT).show();
                     Intent intent = new Intent(RegistroUser.this,Login.class);
                     startActivity(intent);

                 }
             }
             @Override
             public void onFailure(Call<User> call, Throwable t) {

                 Toast.makeText(RegistroUser.this,"Datos incorrectos",Toast.LENGTH_SHORT).show();

             }
         });

     }

     //Guarda la session de usuario
     public void guardarSession(String nombre,String email){
         editor.putString("nombre",nombre);
         editor.putString("email",email);
         editor.apply();
     }
 }