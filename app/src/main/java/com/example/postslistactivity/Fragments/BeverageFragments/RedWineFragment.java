package com.example.postslistactivity.Fragments.BeverageFragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

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
public class RedWineFragment extends BaseFragment {

    private View redWineView;
    private RecyclerView redWineList;
    private ItemsAdapter itemsAdapter;
    private List<NewModel> list;

    private DatabaseOpenHelper databaseOpenHelper;


    public RedWineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        redWineView = inflater.inflate(R.layout.fragment_red_wine, container, false);

        redWineList = redWineView.findViewById(R.id.redWineRVList);
        redWineList.setLayoutManager(new LinearLayoutManager(getContext()));


        databaseOpenHelper = new DatabaseOpenHelper(getContext());

        //Get product list in db when db exists
        list = databaseOpenHelper.getRedWine();
        //Init adapter
        itemsAdapter = new ItemsAdapter(getContext(), list);
        //Set adapter for listview
        redWineList.setAdapter(itemsAdapter);

        return redWineView;
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

                Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
                if (newText != null && TextUtils.getTrimmedLength(newText) > 0) {
                    newText = newText.toLowerCase();
                    List<NewModel> myList = new ArrayList<>();
                    for (NewModel newModel : list) {

                        String title = newModel.getTitle().toLowerCase();
                        String description = newModel.getDescription().toLowerCase();
                        if (title.contains(newText) || description.contains(newText)) {

                            myList.add(newModel);
                            onSerach();
                        }

                    }

                    itemsAdapter.setFilter(myList);
                }
                else {

                    list = databaseOpenHelper.getRedWine();
                    itemsAdapter = new ItemsAdapter(getContext(), list);
                    redWineList.setAdapter(itemsAdapter);
                    itemsAdapter.notifyDataSetChanged();
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

        list = databaseOpenHelper.getSearch();
        itemsAdapter = new ItemsAdapter(getContext(), list);
        redWineList.setAdapter(itemsAdapter);
    }


}
