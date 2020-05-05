package com.example.postslistactivity.Fragments.FoodFragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.postslistactivity.Adapters.MainsPagerAdapter;
import com.example.postslistactivity.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainsFragment extends Fragment {

    private View mainsView;

    public MainsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainsView = inflater.inflate(R.layout.fragment_mains, container, false);

        final TabLayout mainsTabLayout = mainsView.findViewById(R.id.mainsTabLayout);
        TabItem mainsTab = mainsView.findViewById(R.id.mainsTab);
        TabItem sharingMainsTab = mainsView.findViewById(R.id.sharingMainsTab);
        TabItem sidesTab = mainsView.findViewById(R.id.sidesTab);
        final ViewPager mainsViewPager = mainsView.findViewById(R.id.mainsViewPager);

        MainsPagerAdapter mainsPagerAdapter = new MainsPagerAdapter(getChildFragmentManager(), mainsTabLayout.getTabCount());
        mainsViewPager.setAdapter(mainsPagerAdapter);

        mainsTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mainsViewPager));
        mainsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mainsTabLayout));

        return mainsView;
    }

}
