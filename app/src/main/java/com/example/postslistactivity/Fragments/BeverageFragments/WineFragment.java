package com.example.postslistactivity.Fragments.BeverageFragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.postslistactivity.Adapters.ItemsAdapter;
import com.example.postslistactivity.Adapters.WinePagerAdapter;
import com.example.postslistactivity.DatabaseOpenHelper;
import com.example.postslistactivity.Model.NewModel;
import com.example.postslistactivity.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WineFragment extends Fragment {


    private ItemsAdapter itemsAdapter;
    private List<NewModel> list;
    private DatabaseOpenHelper databaseOpenHelper;



    public WineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View getView = inflater.inflate(R.layout.fragment_wine, container, false);

        setHasOptionsMenu(true);

        final TabLayout tabLayout = getView.findViewById(R.id.tabLayout);
        TabItem champagneTab = getView.findViewById(R.id.champagneTab);
        TabItem roseTab = getView.findViewById(R.id.roseTab);
        TabItem whiteTab = getView.findViewById(R.id.whiteTab);
        TabItem redTab = getView.findViewById(R.id.redTab);
        final ViewPager viewPager = getView.findViewById(R.id.viewPager);

        WinePagerAdapter pagerAdapter = new WinePagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



        return getView;
    }


    /*private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DatabaseOpenHelper.DBNAME);
            String outFileName = DatabaseOpenHelper.DBLOCATION + DatabaseOpenHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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

                onSerach();

                if (newText != null && TextUtils.getTrimmedLength(newText) > 0) {
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


    public void onSerach(){

        databaseOpenHelper = new DatabaseOpenHelper(getContext());

        //Check exists database
        File database = getContext().getDatabasePath(DatabaseOpenHelper.DBNAME);
        if(false == database.exists()) {
            databaseOpenHelper.getReadableDatabase();
            //Copy db
            if(copyDatabase(getContext())) {
                Toast.makeText(getContext(), "Copy database success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }

        }
        //Get product list in db when db exists
        list = databaseOpenHelper.getSearch();
        //Init adapter
        itemsAdapter = new ItemsAdapter(getContext(), list);
        //Set adapter for listview
        //recyclerView.setAdapter(itemsAdapter);
    }*/



}
