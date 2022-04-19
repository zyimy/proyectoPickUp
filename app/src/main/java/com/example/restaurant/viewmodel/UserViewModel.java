package com.example.restaurant.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.restaurant.model.User;
import com.example.restaurant.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<User>getUser;

    public UserViewModel(@NonNull Application application){
        super(application);
    }

    public void init(){
        userRepository = new UserRepository();
        getUser =userRepository.getData();
    }

    public void setUserRepository(String email, String contrasena){

        userRepository.buscarUsuario(email,contrasena);
    }

    public  LiveData<User>getObserverUser(){
        return getUser;
    }


}
