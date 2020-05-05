package com.example.postslistactivity.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.postslistactivity.Fragments.FoodFragments.FishFragment;
import com.example.postslistactivity.Fragments.FoodFragments.MeatFragment;
import com.example.postslistactivity.Fragments.FoodFragments.VegetarianFragment;

public class MezedesPagerAdapter extends FragmentPagerAdapter {

    private int numOfTab;

    public MezedesPagerAdapter(@NonNull FragmentManager fm, int numOfTab) {
        super(fm, numOfTab);

        this.numOfTab = numOfTab;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0 :
                return new VegetarianFragment();

            case 1 :
                return new FishFragment();

            case 2 :
                return new MeatFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return numOfTab;
    }
}
