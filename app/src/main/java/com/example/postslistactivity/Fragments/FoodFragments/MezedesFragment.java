package com.example.postslistactivity.Fragments.FoodFragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.postslistactivity.Adapters.MezedesPagerAdapter;
import com.example.postslistactivity.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class MezedesFragment extends Fragment {

    private View mezedesView;

    public MezedesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mezedesView = inflater.inflate(R.layout.fragment_mezedes, container, false);

        final TabLayout mezedesTabLayout = mezedesView.findViewById(R.id.mezedesTabLayout);
        TabItem vegetarianTab = mezedesView.findViewById(R.id.vegetarianTab);
        TabItem fishTab = mezedesView.findViewById(R.id.fishTab);
        TabItem meatTab = mezedesView.findViewById(R.id.meatTab);
        final ViewPager mezedesViewPager = mezedesView.findViewById(R.id.mezedesViewPager);

        MezedesPagerAdapter mezedesPagerAdapter = new MezedesPagerAdapter(getChildFragmentManager(), mezedesTabLayout.getTabCount());
        mezedesViewPager.setAdapter(mezedesPagerAdapter);

        mezedesTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mezedesViewPager));
        mezedesViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mezedesTabLayout));

        return mezedesView;
    }

}
