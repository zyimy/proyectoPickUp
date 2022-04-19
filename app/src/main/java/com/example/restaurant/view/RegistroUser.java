 package com.example.restaurant.view;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.restaurant.R;
import com.example.restaurant.model.User;
import com.example.restaurant.network.ApiService;
import com.example.restaurant.network.RetroInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class RegistroUser extends AppCompatActivity {


     private TextView nombre, email, contrasena;
     private Button ingresarUser;
     private ApiService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_user);
        ingresarUser = findViewById(R.id.btnIngresarUser);

         ingresarUser.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 mostrarUser();
             }
         });
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
}