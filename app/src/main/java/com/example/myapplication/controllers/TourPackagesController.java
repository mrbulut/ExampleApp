package com.example.myapplication.controllers;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.adapters.PackagesTourAdapter;
import com.example.myapplication.entities.Tour;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;

public class TourPackagesController implements IController {

    private RecyclerView tourRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Tour> tourPackagesList= new ArrayList<>();
    private RecyclerView.Adapter adapterPackages;
    private Context mContext;
    private String mUrl;

    public TourPackagesController(Context context,
                                  String url,
                                  RecyclerView mRecyclerView) {
        this.mContext = context;
        this.mUrl = url;
        this.tourRecyclerView = mRecyclerView;
        adapterPackages = new PackagesTourAdapter(mContext,tourPackagesList);
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




                for(int i = 0 ;i < response.length() ; i++){



                    try {

                        tour = new Tour();
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
                        if(tour.getTourStatus().equals("Packages"))
                            tourPackagesList.add(tour);
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }



                }
                progressDialog.dismiss();
                adapterPackages.notifyDataSetChanged();

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

        linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        dividerItemDecoration = new DividerItemDecoration(tourRecyclerView.getContext(), linearLayoutManager.getOrientation());
        tourRecyclerView.setHasFixedSize(true);
        tourRecyclerView.setLayoutManager(linearLayoutManager);
        tourRecyclerView.setAdapter(adapterPackages);

    }
}
