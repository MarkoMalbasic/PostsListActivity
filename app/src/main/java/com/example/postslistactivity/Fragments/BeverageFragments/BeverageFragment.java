package com.example.postslistactivity.Fragments.BeverageFragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import com.example.postslistactivity.Fragments.BaseFragment;
import com.example.postslistactivity.DatabaseOpenHelper;
import com.example.postslistactivity.Model.NewModel;
import com.example.postslistactivity.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BeverageFragment extends BaseFragment implements View.OnClickListener {


    private View beverageView;
    private RecyclerView beverageList;
    private ItemsAdapter itemsAdapter;
    private List<NewModel> list;
    private DatabaseOpenHelper databaseOpenHelper;

    private ScrollView scrollView;
    private Button btnWine, btnCocktail;


    public BeverageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        beverageView = inflater.inflate(R.layout.fragment_beverage, container, false);

        setHasOptionsMenu(true);

        btnWine = beverageView.findViewById(R.id.btnWine);
        btnCocktail = beverageView.findViewById(R.id.btnCocktail);
        btnWine.setOnClickListener(this);
        btnCocktail.setOnClickListener(this);

        scrollView = beverageView.findViewById(R.id.scrollView);

        beverageList = beverageView.findViewById(R.id.beverageRVList);
        beverageList.setHasFixedSize(true);
        beverageList.setLayoutManager(new LinearLayoutManager(getContext()));
        beverageList.setVisibility(View.GONE);

        databaseOpenHelper = new DatabaseOpenHelper(getContext());

        return beverageView;
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
                    beverageList.setVisibility(View.GONE);

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
    public void onSerach() {
        super.onSerach();

        scrollView.setVisibility(View.GONE);
        beverageList.setVisibility(View.VISIBLE);

        //Get product list in db when db exists
        list = databaseOpenHelper.getSearch();
        //Init adapter
        itemsAdapter = new ItemsAdapter(getContext(), list);
        //Set adapter for listview
        beverageList.setAdapter(itemsAdapter);
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnWine :

                WineFragment wineFragment = new WineFragment();
                FragmentManager wineManager = getFragmentManager();
                FragmentTransaction wineTransaction = wineManager.beginTransaction();
                wineTransaction.replace(R.id.frame_layout, wineFragment).addToBackStack(null);
                wineTransaction.commit();
                break;

            case R.id.btnCocktail :

                CocktailFragment cocktailFragment = new CocktailFragment();
                FragmentManager cocktailManager = getFragmentManager();
                FragmentTransaction cocktailTransaction = cocktailManager.beginTransaction();
                cocktailTransaction.replace(R.id.frame_layout, cocktailFragment).addToBackStack(null);
                cocktailTransaction.commit();
                break;
        }

    }

    
}
