package com.example.restaurant.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.restaurant.model.Imagen;
import com.example.restaurant.model.User;
import com.example.restaurant.repository.ImagenRepository;
import com.example.restaurant.repository.UserRepository;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ImagenViewModel extends AndroidViewModel {

    private ImagenRepository imagenRepository;
    private LiveData<List<Imagen>> getImagen;

    public ImagenViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public void init(){
        imagenRepository = new ImagenRepository();
        getImagen =imagenRepository.getData();
    }

    public void setImagenRepository(Long idPack){

        imagenRepository.buscarImagen(idPack);
    }

    public  LiveData<List<Imagen>>getObserverImagen(){

        return getImagen;
    }
}
