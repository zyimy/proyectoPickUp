package com.example.restaurant.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.restaurant.model.User;
import com.example.restaurant.network.ApiService;
import com.example.restaurant.network.RetroInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private ApiService apiService;
    private MutableLiveData<User>data;

    public UserRepository() {
        this.data = new MutableLiveData<>();
    }

    public void buscarUsuario(String email, String contrasena){

        apiService = RetroInstance.getRetrofit().create(ApiService.class);
        apiService.usuarioContrasena(email, contrasena).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                data.postValue(null);

            }
        });

    }

    public LiveData<User>getData(){
        return data;
    }
}
