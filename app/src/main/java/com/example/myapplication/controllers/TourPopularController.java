package com.example.myapplication.controllers;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.adapters.GridAdapter;
import com.example.myapplication.adapters.CustomPagerAdapter;
import com.example.myapplication.entities.Tour;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class TourPopularController implements IController {


    private List<Tour> tourList= new ArrayList<>();
    private int mPageSize = 4;
    private int totalPage;
    private List<View> viewPagerList;
    private ViewPager viewPager0;
    private Context mContext;
    private double size;
    private String mUrl;

    public TourPopularController(Context mContext,String url, ViewPager viewPager0) {
        this.viewPager0 = viewPager0;
        this.mContext = mContext;
        this.mUrl = url;
        generate();
        getData();
    }

    @Override
    public void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,mUrl, null,new Response.Listener<JSONArray>() {
            Tour tour ;
            @Override
            public void onResponse(JSONArray response) {


                tourList.clear();


                for(int i = 0 ;i < response.length() ; i++){

                    tour = new Tour();

                    try {


                        tour.setId(i);
                        tour.setTourStatus(response.getJSONObject(i).getString("tourStatus"));
                        tour.setTourType(response.getJSONObject(i).getString("tourType"));
                        tour.setDiscount(response.getJSONObject(i).getInt("discount"));
                        tour.setPoint(response.getJSONObject(i).getDouble("point"));
                        tour.setTourName(response.getJSONObject(i).getString("tourName"));
                        tour.setTourLocation(response.getJSONObject(i).getString("tourLocation"));
                        tour.setInstructions(response.getJSONObject(i).getString("instructions"));
                        tour.setTourImageArray(response.getJSONObject(i).getJSONArray("tourImageArray"));
                        tour.setTourDescArray(response.getJSONObject(i).getJSONObject("tourDescArray"));
                        tour.setScheduleArray(response.getJSONObject(i).getJSONObject("scheduleArray"));
                        tour.setPriceArray(response.getJSONObject(i).getJSONObject("priceArray"));
                        tour.setIncAndExcArray(response.getJSONObject(i).getJSONObject("incAndExcArray"));
                        tour.setAddInfoArray(response.getJSONObject(i).getJSONObject("addInfoArray"));
                        if(tour.getTourStatus().equals("Popular"))
                            tourList.add(tour);
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }




                }


                size = (double) tourList.size() * 1.0d;
                double pageSize = mPageSize;
                totalPage = (int) Math.ceil(size / pageSize);
                for (int i = 0; i < totalPage; i++) {
                    final GridView gridView = (GridView) View.inflate(mContext, R.layout.gridview_item, null);
                    gridView.setAdapter(new GridAdapter( tourList,mContext, i, mPageSize));
                    viewPagerList.add(gridView);
                }
                CustomPagerAdapter adapter = new CustomPagerAdapter(viewPagerList);
                viewPager0.setAdapter(adapter);
                adapter.notifyDataSetChanged();



                progressDialog.dismiss();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError", String.valueOf(error));
                progressDialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void generate() {
        this.tourList = new ArrayList<Tour>();
        this.viewPagerList = new ArrayList<View>();
    }
}
