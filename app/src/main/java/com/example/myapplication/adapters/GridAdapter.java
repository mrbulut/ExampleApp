package com.example.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapplication.R;
import com.example.myapplication.entities.Tour;
import com.example.myapplication.utill.Transform;
import com.example.myapplication.views.TourActivity;
import com.squareup.picasso.Picasso;


import java.util.List;

public class GridAdapter extends BaseAdapter implements IAdapter {

    private List<Tour> tourList;

    private Context mContext;

    private int mIndex;

    private int mPargerSize;

    public GridAdapter(List<Tour> tourList, Context mContext, int mIndex, int mPargerSize) {
        this.tourList = tourList;
        this.mContext = mContext;
        this.mIndex = mIndex;
        this.mPargerSize = mPargerSize;
    }




    public class ViewHolder{
        ImageView imageView;
        TextView textView;
        ConstraintLayout constraintLayout;
        ViewHolder() {
        }
    }

    @Override
    public int getCount() {
        int size = tourList.size();
        int i = mIndex + 1;
        int i2 = mPargerSize;
        if (size > i * i2) {
            return i2;
        }
        return tourList.size() - (mIndex * mPargerSize);
    }

    @Override
    public Object getItem(int position) {
        return tourList.get((mIndex * mPargerSize) + position);
    }

    @Override
    public long getItemId(int position) {
        return (long) ((this.mIndex * this.mPargerSize) + position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Tour tour = tourList.get(position);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.image_item_popular, null);
            holder.textView =  convertView.findViewById(R.id.image_item_popular_title);
            holder.imageView =  convertView.findViewById(R.id.image_item_popular_background);
            holder.constraintLayout = convertView.findViewById(R.id.popular_item_linearlayout);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(String.valueOf(tour.getTourName()));
        try {
            Picasso.get().load(String.valueOf(tour.getTourImageArray().get(0))).fit().transform(Transform.set()).into(holder.imageView);
            holder.textView.setText(tour.getTourName());
        }catch (Exception e){

        }

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TourActivity.class);
                intent.putExtra("liste", String.valueOf(tourList.get(position))) ;
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
        return convertView;

    }
}
