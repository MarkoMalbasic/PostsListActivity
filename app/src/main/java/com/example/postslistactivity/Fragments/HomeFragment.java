package com.example.postslistactivity.Fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ScrollView;

import com.example.postslistactivity.Adapters.ItemsAdapter;
import com.example.postslistactivity.DatabaseOpenHelper;
import com.example.postslistactivity.Fragments.BeverageFragments.BeverageFragment;
import com.example.postslistactivity.Fragments.FoodFragments.FoodFragment;
import com.example.postslistactivity.Model.NewModel;
import com.example.postslistactivity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private View homeView;
    private RecyclerView homeList;
    private ItemsAdapter itemsAdapter;
    private List<NewModel> list;
    private DatabaseOpenHelper databaseOpenHelper;

    private ScrollView scrollView;
    private Button btnFood, btnBeverage, btnService, btnProfile;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeView = inflater.inflate(R.layout.fragment_home, container, false);

        setHasOptionsMenu(true);

        btnFood = homeView.findViewById(R.id.btnFood);
        btnBeverage = homeView.findViewById(R.id.btnBeverage);
        btnService = homeView.findViewById(R.id.btnService);
        btnProfile = homeView.findViewById(R.id.btnProfile);

        btnFood.setOnClickListener(this);
        btnBeverage.setOnClickListener(this);
        btnService.setOnClickListener(this);
        btnProfile.setOnClickListener(this);

        scrollView = homeView.findViewById(R.id.scrollViewHome);

        homeList = homeView.findViewById(R.id.homeRCView);
        homeList.setHasFixedSize(true);
        homeList.setLayoutManager(new LinearLayoutManager(getContext()));
        homeList.setVisibility(View.GONE);

        databaseOpenHelper = new DatabaseOpenHelper(getContext());

        return homeView;
    }



    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);

        MenuItem mSearchMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) mSearchMenuItem.getActionView();
        searchView.setVisibility(View.GONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText != null && TextUtils.getTrimmedLength(newText) > 0) {
                    onSerach();
                    newText = newText.toLowerCase();
                    List<NewModel> myList = new ArrayList<>();
                    for (NewModel newModel : list) {

                        String title = newModel.getTitle().toLowerCase();
                        String description = newModel.getDescription().toLowerCase();
                        if (title.contains(newText) || description.contains(newText)) {

                            myList.add(newModel);
                        }
                    }

                    itemsAdapter.setFilter(myList);
                }
                else {

                    scrollView.setVisibility(View.VISIBLE);
                    homeList.setVisibility(View.GONE);

                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                    searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                        @Override
                        public boolean onClose() {
                            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                            return false;
                        }
                    });

                }
                return true;
            }
        });
    }



    @Override
    public void onSerach(){
        super.onSerach();

        scrollView.setVisibility(View.GONE);
        homeList.setVisibility(View.VISIBLE);

        //Get product list in db when db exists
        list = databaseOpenHelper.getSearch();
        //Init adapter
        itemsAdapter = new ItemsAdapter(getContext(), list);
        //Set adapter for listview
        homeList.setAdapter(itemsAdapter);
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnFood :

                FoodFragment foodFragment = new FoodFragment();
                FragmentTransaction foodTransaction = getFragmentManager().beginTransaction();
                foodTransaction.replace(R.id.frame_layout, foodFragment).addToBackStack(null);
                foodTransaction.commit();
                break;

            case R.id.btnBeverage :

                BeverageFragment beverageFragment = new BeverageFragment();
                FragmentTransaction beverageTransaction = getFragmentManager().beginTransaction();
                beverageTransaction.replace(R.id.frame_layout, beverageFragment).addToBackStack(null);
                beverageTransaction.commit();
                break;

            case R.id.btnService :

                SequenceFragment sequenceFragment = new SequenceFragment();
                FragmentTransaction sequenceTransaction = getFragmentManager().beginTransaction();
                sequenceTransaction.replace(R.id.frame_layout, sequenceFragment).addToBackStack(null);
                sequenceTransaction.commit();
                break;

            case R.id.btnProfile :
                break;
        }
    }
}
