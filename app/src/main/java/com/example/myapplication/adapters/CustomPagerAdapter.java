package com.example.myapplication.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class CustomPagerAdapter extends PagerAdapter implements IAdapter {
    private List<View> viewList;

    public CustomPagerAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    public int getCount() {
        List list = this.viewList;
        return list != null ? list.size() : 0;
    }

    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        container.addView((View) this.viewList.get(position));
        return this.viewList.get(position);
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
