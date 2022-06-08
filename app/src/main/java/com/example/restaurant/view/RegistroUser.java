 package com.example.restaurant.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.restaurant.NavigationDrawer;
import com.example.restaurant.R;
import com.example.restaurant.model.User;
import com.example.restaurant.network.ApiService;
import com.example.restaurant.network.RetroInstance;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class RegistroUser extends AppCompatActivity {
     private TextView nombre, email, contrasena;
     private Button ingresarUser;
     private ApiService apiService;
     private Toolbar toolbar;
     private ImageView imagenLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_user);
        toolbar = findViewById(R.id.toolbar);
        ingresarUser = findViewById(R.id.btnIngresarUser);
        imagenLogo = findViewById(R.id.imgLogoUser);

        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(true);
        ab.setTitle("Registro");


         ingresarUser.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 mostrarUser();
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
            enviarnombreEmail(nombre.getText().toString(),email.getText().toString());
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
                     Toast.makeText(RegistroUser.this,"Usuario Registrado correctamente",Toast.LENGTH_LONG).show();
                 }
             }
             @Override
             public void onFailure(Call<User> call, Throwable t) {

                 Toast.makeText(RegistroUser.this,"Datos incorrectos",Toast.LENGTH_LONG).show();

             }
         });

     }

     //Enviamos los datos del usuario al perfil del navigation drawer
     public void enviarnombreEmail(String nombre, String email){
         Intent intent = new Intent(RegistroUser.this, Login.class);
         intent.putExtra("nombre",nombre);
         intent.putExtra("email",email);
         startActivity(intent);
     }

     //boton cargar imagen
     public void onClick(View view){
        cargarImagen();
     }


     //Cargar la imagen de la galeria
     private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicacion"),10);
     }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
         super.onActivityResult(requestCode, resultCode, data);
         if(resultCode==RESULT_OK){
            Uri path = data.getData();
            imagenLogo.setImageURI(path);

            
         }

     }
 }