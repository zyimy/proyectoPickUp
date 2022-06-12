package com.example.restaurant.ui.mapa;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.restaurant.R;

import com.example.restaurant.model.PuntosMapa;
import com.example.restaurant.model.Restaurante;
import com.example.restaurant.viewmodel.RestauranteViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class Mapa extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double lat,lon;
    private static String TAG = "GalleryFragment";
    private List<PuntosMapa>doubleList = new ArrayList<>();
    private RestauranteViewModel restauranteViewModel;
    private List<Restaurante>listaRestaurante = new ArrayList<>();



    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        getLocalizacion();

        return view;



    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

    }






    private void getLocalizacion() {
        int permiso = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION);
        if(permiso == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)){
            }else{
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        configurarModel(mMap);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return;
        }


        mMap.setMyLocationEnabled(true);

        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(miUbicacion).title("ubicacion actual")
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.persona)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(miUbicacion));
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(miUbicacion)
                        .zoom(13)
                        .bearing(90)
                        .tilt(10)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                Log.e("longitud",+location.getLongitude()+" latitud: "+location.getLatitude());

            }




            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);


    }


    public void puntos(GoogleMap googleMap){


        final LatLng punto4 = new LatLng(40.29934002958855, -3.832053893065032);
        final LatLng punto5 = new LatLng(40.285722772531024, -3.786391967049797);
        final LatLng punto6 = new LatLng(40.28937304753739, -3.8051114048786);
        final LatLng punto7 = new LatLng(40.29733001225342, -3.8071038155346155);

        googleMap.addMarker(new MarkerOptions().position(punto4).title("Restaurante espaeranza")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.tienda)));
        googleMap.addMarker(new MarkerOptions().position(punto5).title("Restaurante tabola")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.tienda)));

        googleMap.addMarker(new MarkerOptions().position(punto6).title("Restaurante tabola")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.tienda)));

        googleMap.addMarker(new MarkerOptions().position(punto7).title("Restaurante tabola")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.tienda)));


    }

    public void punto(GoogleMap googleMap){
        PuntosMapa pm = new PuntosMapa(40.29934002958855,-3.832053893065032,"Restaurante esperanza","Calle de madrid 4",true);
        PuntosMapa pm1 = new PuntosMapa(40.285722772531024, -3.786391967049797,"Restaurante Tabola","Calle de madrid 2",true);
        PuntosMapa pm2 = new PuntosMapa(40.28937304753739, -3.8051114048786,"Restaurante Andaluza","Calle de madrid 3",false);
        PuntosMapa pm3 = new PuntosMapa(40.29733001225342, -3.8071038155346155,"Restaurante sanchez","Calle de madrid 1",true);

        doubleList.add(pm);
        doubleList.add(pm1);
        doubleList.add(pm2);
        doubleList.add(pm3);

        for (PuntosMapa puntosMapa :doubleList){

            final LatLng position = new LatLng(puntosMapa.getLatitude(), puntosMapa.getLongitud());
            if (!puntosMapa.getDisponible()){
                Log.i("longitud","latitud");
            }else{
                googleMap.addMarker(new MarkerOptions().position(position).title(puntosMapa.getTitulo()+" , "+puntosMapa.getDireccion())
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.tienda)));
            }

        }

    }


    public void configurarModel(GoogleMap googleMap){
        restauranteViewModel = new ViewModelProvider(this).get(RestauranteViewModel.class);
        restauranteViewModel.init();
        restauranteViewModel.getObserverListaRestaurante().observe(this, new Observer<List<Restaurante>>() {
            @Override
            public void onChanged(List<Restaurante> restaurantes) {
                if (restaurantes!=null){
                    listaRestaurante = restaurantes;

                    for (Restaurante mapa:listaRestaurante){
                        final LatLng position = new LatLng(mapa.getLatitud(),mapa.getLongitud());
                        if (!mapa.isDisponible()){
                            Log.i("latitud: ","longitud");
                        }else{
                            googleMap.addMarker(new MarkerOptions().position(position).title(mapa.getNombre()+" , "+mapa.getNombre_calle()+"  "+mapa.getNumero_calle())
                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.tienda)));
                        }
                    }
                }
            }
        });



    }
}