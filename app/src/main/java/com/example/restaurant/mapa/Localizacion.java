package com.example.restaurant.mapa;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.restaurant.NavigationDrawer;
import com.example.restaurant.R;
import com.example.restaurant.ui.mapa.Mapa;

import java.util.List;

public class Localizacion  implements LocationListener {

   NavigationDrawer navigationDrawer;



    public NavigationDrawer getNavigationDrawer(){
        return navigationDrawer;
    }

    public void setNavigationDrawer(NavigationDrawer navigationDrawer){
        this.navigationDrawer= navigationDrawer;

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
       String texto = "Mi ubicacion es: \n"
       +"Latitud =  "+location.getLatitude() + "\n"
       + "Longitud = "+location.getLongitude();



       mapa(location.getLatitude(),location.getLongitude());
    }

    //https://www.youtube.com/watch?v=smPjbSQQu10
   // https://www.youtube.com/watch?v=Y_nR4kZzw-w

    public void mapa(double lat,double lon){
        Mapa fragmentMaps = new Mapa();
        Bundle bundle = new Bundle();
        bundle.putDouble("lat",new Double(lat));
        bundle.putDouble("lon",new Double(lon));
        fragmentMaps.setArguments(bundle);

        FragmentManager fragmentManager = getNavigationDrawer().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.map,fragmentMaps,null);
        fragmentTransaction.commit();
    }


    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);

        switch (status){
            case LocationProvider.AVAILABLE:
                Log.d("debug","LocationProvider.AVAILABLE");
                break;

            case LocationProvider.OUT_OF_SERVICE:
                Log.d("debug","LocationProvider.OUT_OF_SERVICE:");
                break;

            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug","LocationProvider.TEMPORARILY_UNAVAILABLE:");
                    break;


        }
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);

        //tvMensaje.setText("gps Activado");
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);

        //tvMensaje.setText("gps Activado");
    }
}
