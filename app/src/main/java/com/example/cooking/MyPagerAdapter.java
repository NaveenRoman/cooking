package com.example.cooking;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {
    int tabcount;

    public MyPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount=behavior;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new TodayFragment();
            case 1:
                return new HomeFragment();
            case 2:
                return new TopFragment();
            case 3:
                return new ProfileFragment();


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }



}
