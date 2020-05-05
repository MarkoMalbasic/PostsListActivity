package com.example.postslistactivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.postslistactivity.Model.NewModel;
import java.util.ArrayList;
import java.util.List;


public class DatabaseOpenHelper extends SQLiteOpenHelper {


    public static final String DBNAME = "sample.sqlite";
    public static final String DBLOCATION = "/data/data/com.example.postslistactivity/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseOpenHelper(Context context) {
        super(context, DBNAME, null, 5);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }



    public List<NewModel> getChampagne() {
        NewModel model = null;
        List<NewModel> productList = new ArrayList<>();
        Bitmap bt = null;
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM CHAMPAGNE", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //byte[] bytes = cursor.getBlob(1);
            model = new NewModel(cursor.getString(0), cursor.getBlob(1) , cursor.getString(2), cursor.getString(3));
            productList.add(model);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return productList;

    }


    public List<NewModel> getRoseWine() {
        NewModel model = null;
        List<NewModel> productList = new ArrayList<>();
        Bitmap bt = null;
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM ROSEWINE", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //byte[] bytes = cursor.getBlob(1);
            model = new NewModel(cursor.getString(0), cursor.getBlob(1) , cursor.getString(2), cursor.getString(3));
            productList.add(model);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return productList;

    }


    public List<NewModel> getWhiteWine() {
        NewModel model = null;
        List<NewModel> productList = new ArrayList<>();
        Bitmap bt = null;
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM WHITEWINE", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //byte[] bytes = cursor.getBlob(1);
            model = new NewModel(cursor.getString(0), cursor.getBlob(1) , cursor.getString(2), cursor.getString(3));
            productList.add(model);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return productList;

    }


    public List<NewModel> getRedWine() {
        NewModel model = null;
        List<NewModel> productList = new ArrayList<>();
        Bitmap bt = null;
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM REDWINE", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //byte[] bytes = cursor.getBlob(1);
            model = new NewModel(cursor.getString(0), cursor.getBlob(1) , cursor.getString(2), cursor.getString(3));
            productList.add(model);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return productList;

    }


    public List<NewModel> getSearch() {
        NewModel model = null;
        List<NewModel> productList = new ArrayList<>();
        Bitmap bt = null;
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM SEARCH", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //byte[] bytes = cursor.getBlob(1);
            model = new NewModel(cursor.getString(0), cursor.getBlob(1) , cursor.getString(2), cursor.getString(3));
            productList.add(model);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return productList;

    }
}
