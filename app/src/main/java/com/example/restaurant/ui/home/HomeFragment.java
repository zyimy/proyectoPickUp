package com.example.restaurant.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

public class HomeFragment extends Fragment {

    private RecyclerView rv;
    private List<Pack> listaPack = new ArrayList<>();
    private List<Pack> lista = new ArrayList<>();
    private PackViewModel packViewModel;
    private PackAdapter adapter;
    private LinearLayoutManager linearLayoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        configurarModel();
        rv = v.findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(linearLayoutManager);
        rv.setHasFixedSize(true);
        adapter= new PackAdapter(getActivity(),listaPack);
        rv.setAdapter(adapter);

        //lista = (List<Pack>) getArguments().getSerializable("nombre");

    }


    public void configurarAdapter(List<Pack>lista){
       adapter.setListaPack(lista);
       adapter.notifyDataSetChanged();
    }

    public void configurarModel(){
        packViewModel = new ViewModelProvider(this).get(PackViewModel.class);
        packViewModel.init();
        packViewModel.getRespuestaPack().observe(this, new Observer<List<Pack>>() {
            @Override
            public void onChanged(List<Pack> packs) {
                if (packs !=null){
                    configurarAdapter(lista);
                    listaPack = packs;
                    adapter.setListaPack(packs);
                    adapter.notifyDataSetChanged();
                }
            }
        });


    }

}