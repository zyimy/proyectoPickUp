package com.example.restaurant.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.restaurant.model.Imagen;
import com.example.restaurant.network.ApiService;
import com.example.restaurant.network.RetroInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class ImagenRepository {

    private ApiService apiService;
    private MutableLiveData<List<Imagen>> data;

    public ImagenRepository() {
        this.data = new MutableLiveData<>();
    }

    public void buscarImagen(Long idPack){
        apiService = RetroInstance.getRetrofit().create(ApiService.class);
        apiService.getImagenes(idPack).enqueue(new Callback<List<Imagen>>() {
            @Override
            public void onResponse(Call<List<Imagen>> call, Response<List<Imagen>> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Imagen>> call, Throwable t) {
                data.postValue(null);
            }
        });

    }

    public LiveData<List<Imagen>> getData(){
        return data;
    }
}
