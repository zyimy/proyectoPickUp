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
import com.example.restaurant.R;
import com.example.restaurant.model.Restaurante;
import com.example.restaurant.model.User;
import com.example.restaurant.viewmodel.RestauranteViewModel;
import com.example.restaurant.viewmodel.UserViewModel;

public class LoginRestaurante extends AppCompatActivity {


    private RestauranteViewModel restauranteViewModel;
    private EditText email, password;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_restaurante);

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

    //pasar los parametros al metodo del viewModel
    public void  init(){
        String ema = email.getText().toString();
        String pass = password.getText().toString();

        if (email.length() ==0 || password.length() == 0 ) {

            Toast.makeText(LoginRestaurante.this, "Debe rellenar todos los campos", Toast.LENGTH_LONG).show();
        }

        restauranteViewModel.setRestauranteRepository(ema,pass);
    }

    //pasar al activity de registro de restaurante
    public  void registrarse(View view){
        Intent intent = new Intent(LoginRestaurante.this,RegistroRestaurante.class);
        startActivity(intent);
    }

    //Hacer el llamado al service de busqueda de usuario
    public void configurarModel(){
        restauranteViewModel = new ViewModelProvider(this).get(RestauranteViewModel.class);
        restauranteViewModel.init();
        restauranteViewModel.getObserverRestaurante().observe(this, new Observer<Restaurante>() {
            @Override
            public void onChanged(Restaurante restaurante) {
                if (restaurante != null){
                    Toast.makeText(LoginRestaurante.this,"Registrado correctamente",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}