package com.example.restaurant.repository;

import androidx.lifecycle.MutableLiveData;
import com.example.restaurant.model.Pack;
import com.example.restaurant.network.ApiService;
import com.example.restaurant.network.RetroInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class PackRepository {

    private ApiService apiService;

    private  static PackRepository instance;

    public static  PackRepository getInstance(){
        if (instance == null){
            instance = new PackRepository();
        }

        return instance;
    }

    public MutableLiveData<List<Pack>>respuestaApi(){
        MutableLiveData<List<Pack>> datos = new MutableLiveData<>();
        apiService = RetroInstance.getRetrofit().create(ApiService.class);
        apiService.getPack().enqueue(new Callback<List<Pack>>() {
            @Override
            public void onResponse(Call<List<Pack>> call, Response<List<Pack>> response) {
               if (response.body()!=null){
                   datos.postValue(response.body());
               }
            }

            @Override
            public void onFailure(Call<List<Pack>> call, Throwable t) {
                datos.postValue(null);

            }
        });

        return datos;
    }



}