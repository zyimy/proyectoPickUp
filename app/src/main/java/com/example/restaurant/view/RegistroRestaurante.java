package com.example.restaurant.view;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.restaurant.R;
import com.example.restaurant.model.Restaurante;
import com.example.restaurant.network.ApiService;
import com.example.restaurant.network.RetroInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroRestaurante extends AppCompatActivity {

    private TextView nombre, email,contrasena, calle,numeroCalle,codigoPostal;
    private Button ingresar;
    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_restaurante);
        ingresar = findViewById( R.id.btnIngresar);

            ingresar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   createRestaurantes();
                }
            });
    }

    public Restaurante createRestaurantes(){

        nombre = findViewById(R.id.txtNombrePack);
        email = findViewById(R.id.txtDescripcionPack);
        contrasena = findViewById(R.id.txtContrasena);
        calle =  findViewById(R.id.txtDireccionpack);
        numeroCalle = findViewById(R.id.txtPrecioPack);
        codigoPostal = findViewById(R.id.txtCodigoPostal);

        int postal = 0;
        int numero_calle = 0;
        if (nombre.length() ==0 || email.length() == 0 || contrasena.length() == 0 ||
        calle.length() ==0 || numeroCalle.getText().toString().isEmpty() || codigoPostal.getText().toString().isEmpty() ) {
            Toast.makeText(RegistroRestaurante.this, "Debe rellenar todos los campos", Toast.LENGTH_LONG).show();
        }

        try{
          postal = Integer.parseInt(codigoPostal.getText().toString());
          numero_calle = Integer.parseInt(numeroCalle.getText().toString());
        }catch(NumberFormatException ex){
            System.out.println(ex);
        }

        Restaurante restaurante= new Restaurante(postal, contrasena.getText().toString(),
                email.getText().toString() ,
                calle.getText().toString(),
                nombre.getText().toString(),
              numero_calle);

         if (restaurante.getNombre_restaurante().isEmpty() ||
                 restaurante.getEmail().isEmpty()
                 || restaurante.getContrasena().isEmpty() ||
                 restaurante.getNombre_calle().isEmpty() ||
                 restaurante.getNumero_calle()==0
                 || restaurante.getCodigo_postal()==0
         ){

         }else{

             responseApiRestaurante(restaurante);
         }
        return restaurante ;
    }


    public void responseApiRestaurante(Restaurante restaurante){
        apiService = RetroInstance.getRetrofit().create(ApiService.class);
        apiService.getIngresar(restaurante).enqueue(new Callback<Restaurante>() {
            @Override
            public void onResponse(Call<Restaurante> call, Response<Restaurante> response) {
                if (response!= null){
                    Toast.makeText(RegistroRestaurante.this,"Registrado correctamente",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Restaurante> call, Throwable t) {

            }
        });

    }

}
