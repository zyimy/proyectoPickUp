package com.example.restaurant.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.restaurant.NavigationDrawer;
import com.example.restaurant.R;
import com.example.restaurant.adapter.SliderAdapter;
import com.example.restaurant.model.Imagen;
import com.example.restaurant.model.User;
import com.example.restaurant.ui.home.HomeFragment;
import com.example.restaurant.viewmodel.ImagenViewModel;
import com.example.restaurant.viewmodel.UserViewModel;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class DescripcionPack extends AppCompatActivity {

    private ImagenViewModel imagenViewModel;
    private List<Imagen>sliderImagenes = new ArrayList<>();
    private SliderAdapter adapter;
    private TextView nombre,descripcion,direccion,precio;
    private Button volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_pack);
      volver = findViewById(R.id.btnVolver);
       configurarModel();
       init();
       inicializarSlider();

       volver.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=  new Intent(DescripcionPack.this, NavigationDrawer.class);
               startActivity(intent);
           }
       });

    }

//Pasamos el id al servicio que nos trae la lista imagenes de un determinado pack
    public void  init(){
        nombre = findViewById(R.id.lblNombreRestaurante);
        descripcion = findViewById(R.id.lblDescripcionP);
        direccion = findViewById(R.id.lblDireccionP);
        precio = findViewById(R.id.lblPreciop);
        Bundle bundle = getIntent().getExtras();
        Long idPack = bundle.getLong("id_pack");
        String non = bundle.getString("nombre");
        String descrip = bundle.getString("descripcion");
        String direcc = bundle.getString("direccion");
        double preci = bundle .getDouble("precio");
        String pre = String.valueOf(preci);
        nombre.setText(non);
        descripcion.setText(descrip);
        direccion.setText(direcc);
        precio.setText(pre);
        imagenViewModel.setImagenRepository(idPack);
    }


    //Inicializamos el slider
    public void inicializarSlider(){
        // initializing the slider view.
        SliderView sliderView = findViewById(R.id.slider);
        // passing this array list inside our adapter class.
        adapter = new SliderAdapter(this, sliderImagenes);
        //below method is used to set auto cycle direction in left to right direction you can change according to requirement.
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        //below method is used to setadapter to sliderview.
        sliderView.setSliderAdapter(adapter);
        //below method is use to set scroll time in seconds.
        sliderView.setScrollTimeInSec(3);
        //to set it scrollable automatically we use below method.
        sliderView.setAutoCycle(true);
        //to start autocycle below method is used.
        sliderView.startAutoCycle();

    }

    //observer de la lista de imagenes
    public void configurarModel(){
        imagenViewModel = new ViewModelProvider(this).get(ImagenViewModel.class);
        imagenViewModel.init();
        imagenViewModel.getObserverImagen().observe(this, new Observer<List<Imagen>>() {
            @Override
            public void onChanged(List<Imagen> imagenes) {
                if (!imagenes.isEmpty()){
                  sliderImagenes = imagenes;
                  adapter.setListaImagen(sliderImagenes);

                }
            }
        });

    }
}