package com.calibrage.a3ffarmerapp.Adapters;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
public class PagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> tabTitles = new ArrayList<>();
    public void addFrag(Fragment fragments,String title)
    {
        this.fragments.add(fragments);
        this.tabTitles.add(title);
    }
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override    public int getCount() {
        return fragments.size();
    }

    @Override    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
}
