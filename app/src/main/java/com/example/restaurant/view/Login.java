package com.example.restaurant.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.restaurant.MenuPrincipal;
import com.example.restaurant.NavigationDrawer;
import com.example.restaurant.R;
import com.example.restaurant.model.User;
import com.example.restaurant.viewmodel.UserViewModel;

public class Login extends AppCompatActivity {

    private UserViewModel userViewModel;
    private EditText email, password;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin =findViewById(R.id.btnLoginRestaurante);
        email = findViewById(R.id.txtEmailRestaurante);
        password = findViewById(R.id.textpasswordRestaurante);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                configurarModel();
                init();
            }
        });

    }

    public void  init(){
        String ema = email.getText().toString();
        String pass = password.getText().toString();

        if (email.length() ==0 || password.length() == 0 ) {

            Toast.makeText(Login.this, "Debe rellenar todos los campos", Toast.LENGTH_LONG).show();
        }

        userViewModel.setUserRepository(ema,pass);
    }

    public  void registrarse(View view){
        Intent intent = new Intent(Login.this,RegistroRestaurante.class);
        startActivity(intent);
    }

    public void configurarModel(){
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.init();
       userViewModel.getObserverUser().observe(this, new Observer<User>() {
           @Override
           public void onChanged(User user) {
               if (user != null){
                   Toast.makeText(Login.this,"Registrado correctamente",Toast.LENGTH_LONG).show();
                   Intent intent = new Intent(Login.this, NavigationDrawer.class);
                   startActivity(intent);

               }
           }
       });

    }
}