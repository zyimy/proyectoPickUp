package com.example.restaurant.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restaurant.R;
import com.example.restaurant.adapter.PackAdapter;
import com.example.restaurant.model.Pack;
import com.example.restaurant.viewmodel.PackViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private List<Pack>listaPack = new ArrayList<>();
    private PackViewModel packViewModel;
    private PackAdapter adapter;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public  void iniciar(){
        rv = findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setHasFixedSize(true);
        adapter= new PackAdapter(MainActivity.this,listaPack);
        rv.setAdapter(adapter);
    }

    public void configurarModel(){
        packViewModel = new ViewModelProvider(this).get(PackViewModel.class);
        packViewModel.init();
        packViewModel.getRespuestaPack().observe(this, new Observer<List<Pack>>() {
            @Override
            public void onChanged(List<Pack> packs) {
                if (packs !=null){
                    listaPack = packs;
                    adapter.setListaPack(packs);
                    adapter.notifyDataSetChanged();
                }
            }
        });


    }
}