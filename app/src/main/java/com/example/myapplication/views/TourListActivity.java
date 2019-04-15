package com.example.myapplication.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import com.example.myapplication.R;
import com.example.myapplication.controllers.TourNewController;
import com.example.myapplication.controllers.TourPackagesController;
import com.example.myapplication.controllers.TourPopularController;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

public class TourListActivity extends FragmentActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.novigation_discover:
                    return true;
                case R.id.navigation_attractions:
                    return true;
                case R.id.navigation_planner:
                    return true;
                case R.id.navigation_notification:
                    return true;
                case R.id.navigation_more:
                    return true;
            }
            return false;
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_list);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        new TourNewController(this,getResources().getString(R.string.api_link),(DiscreteScrollView) findViewById(R.id.item_picker));
        new TourPopularController(this,getResources().getString(R.string.api_link),(ViewPager) findViewById(R.id.popularViewPager));
        new TourPackagesController(this,getResources().getString(R.string.api_link),(RecyclerView) findViewById(R.id.tourPackagesRV));










    }



}
