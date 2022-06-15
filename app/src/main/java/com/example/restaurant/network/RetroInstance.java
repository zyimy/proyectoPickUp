package com.example.restaurant.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {

    private  static Retrofit retrofit;

    public static  String BASE_URL ="http://192.168.1.39:8080/";
      //192.168.1.40
    public static  Retrofit getRetrofit(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

/*
    public void setDataToNavigationDrawer(User user) {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        TextView txtV_Username = (TextView) navigationView.getHeaderView(0).findViewById(R.id.txtV_username);
        TextView txtV_Email = (TextView) navigationView.getHeaderView(0).findViewById(R.id.txtV_email);
        txtV_Username.setText(user.getUsername());
        txtV_Email.setText(user.getEmail());
    }
 */