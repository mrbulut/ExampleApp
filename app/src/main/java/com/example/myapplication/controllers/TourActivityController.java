package com.example.myapplication.controllers;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.ImageSliderAdapter;
import com.example.myapplication.entities.Tour;
import com.example.myapplication.utill.PicassoImageLoadingService;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import ss.com.bannerslider.Slider;

public class TourActivityController {


    private Activity mContext;
    private Tour mTour;

    public TourActivityController(Activity mContext, Tour mTour) {
        this.mContext = mContext;
        this.mTour = mTour;
    }

    public void setupPage() throws JSONException {
        Slider.init(new PicassoImageLoadingService());
        final Slider slider =  mContext.findViewById(R.id.imageSliderPager);
        slider.setAdapter(new ImageSliderAdapter(mTour.getTourImageArray()));

        generatePanels(
                R.id.include_schedule,
                mTour.getScheduleArray().getString("topText"),
                jsonObjectConvertToString(mTour.getTourDescArray().getString("features")),
                mTour.getScheduleArray().getString("bottomText")
                );

        generatePanels(
                R.id.include_pricing,
                mTour.getPriceArray().getString("price"),
                null,
                mTour.getPriceArray().getString("bottomText")
        );


        generatePanels(
                R.id.include_addandinfo,
                mTour.getAddInfoArray().getString("topText"),
                jsonObjectConvertToString(mTour.getAddInfoArray().getString("features")),
                mTour.getAddInfoArray().getString("bottomText")
        );

        generatePanels(
                R.id.include_instructions,
                mTour.getInstructions(),
                "",
                ""

        );

        generatePanels(
                R.id.include_incandexc,
               mTour.getIncAndExcArray().getString("inc"),
                mTour.getIncAndExcArray().getString("noinc"),
               ""
        );





    }






    private void generatePanels(int include, String topText, String features, String bottomText){
        View child = mContext.findViewById(include);
        TextView topTextTV =  child.findViewById(R.id.topText);
        TextView featuresTV =  child.findViewById(R.id.features);
        TextView bottomTextTV =  child.findViewById(R.id.bottomText);
        final ImageView openButton = child.findViewById(R.id.openButton);
        final LinearLayout contentLinearLayout = child.findViewById(R.id.contentLinearLayout);
        final ConstraintLayout titleConstraintLayout = (ConstraintLayout) child.findViewById(R.id.titleLinearLayout);
        final ViewGroup.LayoutParams params = contentLinearLayout.getLayoutParams();
        topTextTV.setText(topText);
        featuresTV.setText(features);
        bottomTextTV.setText(bottomText);
        titleConstraintLayout.setClickable(true);



        titleConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contentLinearLayout.getVisibility()==View.INVISIBLE){
                    contentLinearLayout.setVisibility(View.VISIBLE);
                    params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    contentLinearLayout.setLayoutParams(params);
                    Picasso.get().load(R.drawable.minus).into(openButton);
                }else{
                    contentLinearLayout.setVisibility(View.INVISIBLE);
                    params.height =0;
                    contentLinearLayout.setLayoutParams(params);
                    Picasso.get().load(R.drawable.plus).into(openButton);
                }


            }
        });

    }

    private String jsonObjectConvertToString(String features){
        if(features==null){
            String result = "";
            JSONObject featues = null;
            try {
                featues = new JSONObject(features);
                for(int i = 0 ; i<featues.length();i++){
                    result = result + "-" + String.valueOf(featues.get(String.valueOf(i))) + "\n";
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return  result;
        }else{
            return "";
        }

    }
}
