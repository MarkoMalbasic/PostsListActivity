package com.example.postslistactivity.Fragments.FoodFragments;


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
import com.example.postslistactivity.Fragments.BaseFragment;
import com.example.postslistactivity.Model.NewModel;
import com.example.postslistactivity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFragment extends BaseFragment implements View.OnClickListener {

    private View foodView;
    private RecyclerView foodList;
    private ItemsAdapter itemsAdapter;
    private List<NewModel> list;
    private DatabaseOpenHelper databaseOpenHelper;

    private ScrollView foodScrollView;
    private Button btnMezedes, btnMains, btnDesserts;



    public FoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        foodView = inflater.inflate(R.layout.fragment_food, container, false);

        setHasOptionsMenu(true);

        btnMezedes = foodView.findViewById(R.id.btnMezedes);
        btnMains = foodView.findViewById(R.id.btnMains);
        btnDesserts = foodView.findViewById(R.id.btnDesserts);

        btnMezedes.setOnClickListener(this);
        btnMains.setOnClickListener(this);
        btnDesserts.setOnClickListener(this);

        foodScrollView = foodView.findViewById(R.id.foodScrollView);

        foodList = foodView.findViewById(R.id.foodRVList);
        foodList.setHasFixedSize(true);
        foodList.setLayoutManager(new LinearLayoutManager(getContext()));
        foodList.setVisibility(View.GONE);

        databaseOpenHelper = new DatabaseOpenHelper(getContext());


        return foodView;
    }



    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);

        MenuItem mSearchMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) mSearchMenuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText != null && TextUtils.getTrimmedLength(newText) > 0){
                    onSearch();
                    newText = newText.toLowerCase();
                    List<NewModel> myList = new ArrayList<>();
                    for (NewModel newModel : list){

                        String title = newModel.getTitle().toLowerCase();
                        String description = newModel.getDescription().toLowerCase();
                        if (title.contains(newText) || description.contains(newText)){

                            myList.add(newModel);
                        }
                    }

                    itemsAdapter.setFilter(myList);

                }else {

                    foodScrollView.setVisibility(View.VISIBLE);
                    foodList.setVisibility(View.GONE);

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



    private void onSearch() {

        foodScrollView.setVisibility(View.GONE);
        foodList.setVisibility(View.VISIBLE);

        //Get product list in db when db exists
        list = databaseOpenHelper.getSearch();
        //Init adapter
        itemsAdapter = new ItemsAdapter(getContext(), list);
        //Set adapter for listview
        foodList.setAdapter(itemsAdapter);

    }



    @Override
    public void onClick(View view) {

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        switch (view.getId()){

            case R.id.btnMezedes :

                fragmentTransaction.replace(R.id.frame_layout, new MezedesFragment()).addToBackStack(null);
                fragmentTransaction.commit();
                break;

            case R.id.btnMains :

                fragmentTransaction.replace(R.id.frame_layout, new MainsFragment()).addToBackStack(null);
                fragmentTransaction.commit();
                break;

            case R.id.btnDesserts :

                fragmentTransaction.replace(R.id.frame_layout, new DessertsFragment()).addToBackStack(null);
                fragmentTransaction.commit();
                break;
        }
    }
}
