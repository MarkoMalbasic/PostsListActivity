package com.example.postslistactivity.Fragments.FoodFragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.postslistactivity.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeatFragment extends Fragment {


    public MeatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meat, container, false);
    }

}
