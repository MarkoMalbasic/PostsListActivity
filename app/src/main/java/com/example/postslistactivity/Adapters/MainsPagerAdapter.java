package com.example.postslistactivity.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.postslistactivity.Fragments.FoodFragments.IndividualMainsFragment;
import com.example.postslistactivity.Fragments.FoodFragments.SharingMainsFragment;
import com.example.postslistactivity.Fragments.FoodFragments.SidesFragment;

public class MainsPagerAdapter extends FragmentPagerAdapter {

    private int numOfTab;

    public MainsPagerAdapter(@NonNull FragmentManager fm, int numOfTab) {
        super(fm, numOfTab);

        this.numOfTab = numOfTab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0 :
                return new IndividualMainsFragment();

            case 1 :
                return new SharingMainsFragment();

            case 2 :
                return new SidesFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return numOfTab;
    }
}
