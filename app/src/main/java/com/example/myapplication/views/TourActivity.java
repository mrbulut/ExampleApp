package com.example.myapplication.views;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.example.myapplication.R;
import com.example.myapplication.controllers.TourActivityController;
import com.example.myapplication.entities.Tour;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TourActivity extends AppCompatActivity  {


    private Tour tour=new Tour();
    private TextView tourTypeTV,tourTitleTV,discountTV,realPriceTV,fakePriceTV,tourDescTopTV,tourDescFeaturesTV,tourDescBottomTV,tourPointTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);


        tourTypeTV = findViewById(R.id.tourTypeTV);
        tourTitleTV = findViewById(R.id.tourTitleTV);
        discountTV = findViewById(R.id.discountTV);
        realPriceTV = findViewById(R.id.realPriceTv);
        fakePriceTV = findViewById(R.id.fakePriceTV);
        tourDescTopTV = findViewById(R.id.tourDescTopTV);
        tourDescFeaturesTV = findViewById(R.id.tourDescFeaturesTV);
        tourDescBottomTV = findViewById(R.id.tourDescBottomText);
        tourPointTV = findViewById(R.id.pointTV);


        try {
            tour.setId(getIntent().getExtras().getInt("tourId"));
            tour.setDiscount(getIntent().getExtras().getInt("discount"));
            tour.setPoint(getIntent().getExtras().getDouble("point"));
            tour.setTourName(getIntent().getExtras().getString("tourName"));
            tour.setTourType(getIntent().getExtras().getString("tourType"));
            tour.setTourLocation(getIntent().getExtras().getString("tourLocation"));

            tour.setTourImageArray(new JSONArray(getIntent().getExtras().getString("tourImageArray")));
            tour.setTourDescArray(new JSONObject(getIntent().getExtras().getString("tourDescArray")));
            tour.setPriceArray(new JSONObject(getIntent().getExtras().getString("priceArray")));
            tour.setScheduleArray(new JSONObject(getIntent().getExtras().getString("scheduleArray")));
            tour.setIncAndExcArray(new JSONObject(getIntent().getExtras().getString("incAndExcArray")));
            tour.setAddInfoArray(new JSONObject(getIntent().getExtras().getString("addInfoArray")));


            tourTypeTV.setText(tour.getTourType());
            tourTitleTV.setText(tour.getTourName());
            discountTV.setText("%" + String.valueOf(tour.getDiscount()));
            tourPointTV.setText(String.valueOf(tour.getPoint()));
            String price = String.valueOf(tour.getPriceArray().getString("price"));
            realPriceTV.setText("£" + price);
            float discount = Float.parseFloat(String.valueOf(tour.getDiscount())) / 100;
            float fakePrice = Float.parseFloat(price) * discount;
            fakePrice = Float.parseFloat(price) + fakePrice + fakePrice;
            fakePriceTV.setText("£" + String.valueOf((int) fakePrice));
            fakePriceTV.setPaintFlags(fakePriceTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            tourDescTopTV.setText(tour.getTourDescArray().getString("topText"));
            String result = "";
            JSONObject featues = new JSONObject(tour.getTourDescArray().getString("features"));
            for (int i = 0; i < featues.length(); i++) {
                result = result + "-" + String.valueOf(featues.get(String.valueOf(i))) + "\n";
            }
            tourDescFeaturesTV.setText(result);
            tourDescBottomTV.setText(tour.getTourDescArray().getString("bottomText"));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        TourActivityController tourActivityController = new TourActivityController(this, tour);
        try {
            tourActivityController.setupPage();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public void closeClick(View view) {
        finish();
    }
}
