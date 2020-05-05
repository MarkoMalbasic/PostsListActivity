package com.example.postslistactivity.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.postslistactivity.Fragments.BeverageFragments.ChampagneFragment;
import com.example.postslistactivity.Fragments.BeverageFragments.RedWineFragment;
import com.example.postslistactivity.Fragments.BeverageFragments.RoseWineFragment;
import com.example.postslistactivity.Fragments.BeverageFragments.WhiteWineFragment;

public class WinePagerAdapter extends FragmentPagerAdapter {

    private int numOfTab;

    public WinePagerAdapter(@NonNull FragmentManager fm, int numOfTab) {
        super(fm, numOfTab);

        this.numOfTab = numOfTab;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0 :
                return new ChampagneFragment();

            case 1 :
                return new RoseWineFragment();

            case 2 :
                return new WhiteWineFragment();

            case 3 :
                return new RedWineFragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        return numOfTab;
    }
}
