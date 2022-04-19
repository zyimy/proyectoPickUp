package com.example.restaurant.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.restaurant.model.Pack;
import com.example.restaurant.model.Restaurante;
import com.example.restaurant.model.RestaurantePack;
import com.example.restaurant.model.User;
import com.example.restaurant.network.ApiService;
import com.example.restaurant.network.RetroInstance;
import com.example.restaurant.repository.RestauranteRepository;
import com.example.restaurant.repository.UserRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class RestauranteViewModel extends AndroidViewModel {

    private RestauranteRepository restauranteRepository;
    private LiveData<Restaurante>getRestaurante;
    private LiveData<List<Restaurante>> data;

    public RestauranteViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        restauranteRepository = new RestauranteRepository();
        getRestaurante =restauranteRepository.getData();
        data= restauranteRepository.respuestaApi();
    }

    public void setRestauranteRepository(String email, String contrasena){

        restauranteRepository.buscarRestaurante(email,contrasena);
    }

    public LiveData<Restaurante> getObserverRestaurante(){

        return getRestaurante;
    }

    public LiveData<List<Restaurante>> getObserverListaRestaurante(){

        return data;
    }


}
