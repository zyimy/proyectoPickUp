package com.example.restaurant;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.restaurant.databinding.ActivityNavigationDrawerBinding;

public class NavigationDrawer extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavigationDrawerBinding binding;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView nomb,ema;
    private static final long MIN_TIME = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavigationDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        nomb = findViewById(R.id.lblNombreHeader);
        ema = findViewById(R.id.lblEmailHeader);

        sharedPreferences = this.getSharedPreferences("sesiones", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();



        setSupportActionBar(binding.appBarNavigationDrawer.toolbar);
        binding.appBarNavigationDrawer.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putBoolean("sesion",false);
                editor.apply();
                Snackbar.make(view, "Inicio de session cerrada", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                          finish();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();

        sharedPreferences = this.getSharedPreferences("usuarios", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        nomb =navigationView.getHeaderView(0).findViewById(R.id.lblNombreHeader);
        ema = navigationView.getHeaderView(0).findViewById(R.id.lblEmailHeader);



        String nombre =  sharedPreferences.getString("nombre","");
        String em = sharedPreferences.getString("email","");

        if (nombre!=null){
            nomb.setText(nombre);
            ema.setText(em);

        }


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_drawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_drawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }






}