package com.example.restaurant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.restaurant.R;
import com.example.restaurant.model.Imagen;
import com.example.restaurant.model.Restaurante;
import com.smarteist.autoimageslider.SliderViewAdapter;


import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {
    //list for storing urls of images.
    private  List<Imagen> mSliderItems;

    //Constructor
    public SliderAdapter(Context context, List<Imagen> sliderDataArrayList) {
        this.mSliderItems = sliderDataArrayList;
    }

    public void setListaImagen(List<Imagen>listaPack){
        this.mSliderItems = listaPack;
        notifyDataSetChanged();

    }

    //We are inflating the slider_layout inside on Create View Holder method.
    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
        return new SliderAdapterViewHolder(inflate);
    }

    //Inside on bind view holder we will set data to item of Slider View.
    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {

        final Imagen sliderItem = mSliderItems.get(position);

        //Glide is use to load image from url in your imageview.
        Glide.with(viewHolder.itemView)
                .load(sliderItem.getImagen())
                .fitCenter()
                .into(viewHolder.imageViewBackground);


    }

    //this method will return the count of our list.
    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    static class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
        //Adapter class for initializing the views of our slider view.
        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.myimage);
            this.itemView = itemView;
        }
    }

}
