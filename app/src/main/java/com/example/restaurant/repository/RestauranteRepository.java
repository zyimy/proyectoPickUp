package com.example.restaurant.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.restaurant.model.Restaurante;
import com.example.restaurant.model.RestaurantePack;
import com.example.restaurant.network.ApiService;
import com.example.restaurant.network.RetroInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class RestauranteRepository {

    private ApiService apiService;
    private MutableLiveData<Restaurante> data;

    public RestauranteRepository() {
        this.data = new MutableLiveData<>();
    }

    public void buscarRestaurante(String email, String contrasena){
        apiService = RetroInstance.getRetrofit().create(ApiService.class);
        apiService.usuarioRestaurante(email, contrasena).enqueue(new Callback<Restaurante>() {
            @Override
            public void onResponse(Call<Restaurante> call, Response<Restaurante> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Restaurante> call, Throwable t) {
                data.postValue(null);
            }
        });

    }

    public LiveData<Restaurante> getData(){
        return data;
    }


    public MutableLiveData<List<Restaurante>> respuestaApi(){
        MutableLiveData<List<Restaurante>> datos = new MutableLiveData<>();
        apiService = RetroInstance.getRetrofit().create(ApiService.class);
        apiService.getRestaurante().enqueue(new Callback<List<Restaurante>>() {
            @Override
            public void onResponse(Call<List<Restaurante>> call, Response<List<Restaurante>> response) {
                if (response.body()!=null){
                    datos.postValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Restaurante>> call, Throwable t) {
                datos.postValue(null);
            }
        });

        return datos;
    }
}
