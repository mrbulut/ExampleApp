package com.example.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.entities.Tour;
import com.example.myapplication.utill.Transform;


import com.example.myapplication.views.TourActivity;
import com.squareup.picasso.Picasso;
import java.util.List;

public class PackagesTourAdapter extends RecyclerView.Adapter<PackagesTourAdapter.ViewHolder> implements IAdapter{

    private List<Tour> tourList;

    private Context mContext;

    public PackagesTourAdapter(Context mContext, List<Tour> tourList) {
        this.mContext = mContext;
        this.tourList = tourList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.image_item_packages, viewGroup, false);
        return new ViewHolder(mView);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

         final Tour tour = tourList.get(i);

        try {
           Picasso.get().load(String.valueOf(tour.getTourImageArray().get(0))).fit().transform(Transform.set()).into(viewHolder.tourBackground);
           viewHolder.tourName.setText(tour.getTourName());
        }catch (Exception e){

        }


        viewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TourActivity.class);
                intent.putExtra("liste", String.valueOf(tourList.get(i))) ;
                intent.putExtra("tourId", tour.getId()) ;
                intent.putExtra("discount", tour.getDiscount()) ;
                intent.putExtra("point", tour.getPoint()) ;
                intent.putExtra("tourName", tour.getTourName()) ;
                intent.putExtra("tourType", tour.getTourType()) ;
                intent.putExtra("tourLocation", tour.getTourLocation()) ;
                intent.putExtra("tourImageArray", String.valueOf(tour.getTourImageArray())) ;
                intent.putExtra("tourDescArray", String.valueOf(tour.getTourDescArray())) ;
                intent.putExtra("priceArray", String.valueOf(tour.getPriceArray())) ;
                intent.putExtra("scheduleArray", String.valueOf(tour.getScheduleArray())) ;
                intent.putExtra("incAndExcArray", String.valueOf(tour.getIncAndExcArray())) ;
                intent.putExtra("addInfoArray", String.valueOf(tour.getAddInfoArray())) ;
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView tourBackground;
        TextView tourName;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tourBackground = itemView.findViewById(R.id.image_item_packages_background);
            tourName = itemView.findViewById(R.id.image_item_packages_title);
            constraintLayout = itemView.findViewById(R.id.item_packages_Cardview);
        }
    }

}