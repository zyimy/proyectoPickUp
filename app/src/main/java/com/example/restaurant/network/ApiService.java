package com.example.restaurant.network;

import com.example.restaurant.model.*;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import java.util.HashMap;
import java.util.List;

public interface ApiService {

    @GET("pack/lista")
    Call<List<Pack>>getPack();

    @GET("restaurante/listaPack")
    Call<List<Restaurante>>getPackRestaurante();

    @GET("imagen/listaImagen/{idPack}")
    Call<List<Imagen>>getImagenes(@Path("idPack")Long idPack);

    @POST("restaurante/guardar")
    Call<Restaurante> getIngresar(@Body Restaurante restaurante);


    @POST("user")
    Call<User> getIngresarUser(@Body User user);

    @GET("user/usuario/{email}/{contrasena}")
    Call<User> usuarioContrasena(@Path("email") String email,
                                  @Path("contrasena") String contrasena);

    @GET("restaurante/usuario/{email}/{contrasena}")
    Call<Restaurante> usuarioRestaurante(@Path("email") String email,
                                 @Path("contrasena") String contrasena);


    @POST("pack/{idRestaurante}")
    Call<Pack>registrarPack(@Body Pack pack, @Path("idRestaurante") Long idRestaurante);





}
