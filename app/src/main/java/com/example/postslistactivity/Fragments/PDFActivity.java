package com.example.postslistactivity.Fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;

import com.example.postslistactivity.R;
import com.github.barteksc.pdfviewer.PDFView;

public class PDFActivity extends AppCompatActivity {

    PDFView pdfView;
    private int mCurrentPage = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        pdfView = findViewById(R.id.pdfView);


        int position = 0;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            position = bundle.getInt("position"); // String Which are send through intent
        }

        if (position == 0) {

            pdfView.fromAsset("GeneralWine.pdf")
                    .pageFling(true)
                    .defaultPage(mCurrentPage)
                    .swipeHorizontal(true)
                    .load();
        }

        else if (position == 1){

            pdfView.fromAsset("GeneralSpirits.pdf")
                    .pageFling(true)
                    .defaultPage(mCurrentPage)
                    .swipeHorizontal(true)
                    .load();
        }
    }


}

