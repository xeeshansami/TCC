package com.paxees.tcc.views.adapters;


import com.paxees.tcc.views.fragments.mainFragments.IndoorFragment;
import com.paxees.tcc.views.fragments.mainFragments.OutdoorFragment;
import com.paxees.tcc.views.fragments.mainFragments.RecommendationFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new IndoorFragment();
            case 1:
                return new OutdoorFragment();
            case 2:
                return new RecommendationFragment();
            default:
                return new IndoorFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Indoor";
            case 1:
                return "Outdoor";
            case 2:
                return "Recommendation";
            default:
                return "";
        }
    }

}