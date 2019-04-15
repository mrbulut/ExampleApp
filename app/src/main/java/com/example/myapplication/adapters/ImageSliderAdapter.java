package com.example.myapplication.adapters;


import org.json.JSONArray;
import org.json.JSONException;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class ImageSliderAdapter extends SliderAdapter implements IAdapter {
    private JSONArray mResources;

    public ImageSliderAdapter(JSONArray resources) {
        mResources = resources;
    }


    @Override
    public int getItemCount() {
        return mResources.length();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {

        try {
            imageSlideViewHolder.bindImageSlide(mResources.getString(position));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

