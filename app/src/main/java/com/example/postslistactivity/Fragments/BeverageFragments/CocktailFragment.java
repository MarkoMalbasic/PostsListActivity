package com.example.postslistactivity.Fragments.BeverageFragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.postslistactivity.Adapters.ItemsAdapter;
import com.example.postslistactivity.Fragments.BaseFragment;
import com.example.postslistactivity.DatabaseOpenHelper;
import com.example.postslistactivity.Model.NewModel;
import com.example.postslistactivity.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CocktailFragment extends BaseFragment {

    private View cocktailView;
    private RecyclerView cocktailList;
    private List<NewModel> list;
    private ItemsAdapter itemsAdapter;

    private DatabaseOpenHelper databaseOpenHelper;


    public CocktailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        cocktailView = inflater.inflate(R.layout.fragment_cocktail, container, false);

        cocktailList = cocktailView.findViewById(R.id.cocktailRVList);
        cocktailList.setLayoutManager(new LinearLayoutManager(getContext()));


        databaseOpenHelper = new DatabaseOpenHelper(getContext());


        return cocktailView;
    }

}
