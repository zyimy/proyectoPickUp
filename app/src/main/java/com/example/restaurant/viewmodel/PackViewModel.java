package com.example.restaurant.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.restaurant.model.Pack;
import com.example.restaurant.network.ApiService;
import com.example.restaurant.repository.PackRepository;


import java.util.List;

public class PackViewModel extends ViewModel {

    private ApiService apiService;
    private PackRepository packRepository;
    private MutableLiveData<List<Pack>> data;



    public  void init(){
        if (data!=null){
            return;
        }else{
            packRepository = PackRepository.getInstance();
            data = packRepository.respuestaApi();
        }
    }


    public LiveData<List<Pack>>getRespuestaPack(){
        return data;
    }

}
