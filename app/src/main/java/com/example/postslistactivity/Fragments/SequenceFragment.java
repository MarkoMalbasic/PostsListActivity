package com.example.postslistactivity.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.postslistactivity.R;
import com.github.barteksc.pdfviewer.PDFView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SequenceFragment extends Fragment implements View.OnClickListener {

    private View sequenceView;
    private TextView txt2, txt3;
    int position;
    PDFView pdfView;
    ScrollView sv;


    public SequenceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sequenceView = inflater.inflate(R.layout.fragment_sequence, container, false);

        pdfView = sequenceView.findViewById(R.id.pdfView);
        sv = sequenceView.findViewById(R.id.sv);

        txt2 = sequenceView.findViewById(R.id.txt2);
        txt3 = sequenceView.findViewById(R.id.txt3);
        txt2.setOnClickListener(this);
        txt3.setOnClickListener(this);

        return sequenceView;
    }


    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getContext(), PDFActivity.class);

        switch (v.getId()){

            case R.id.txt2 :

                intent.putExtra("position", 0);
                startActivity(intent);
                break;

            case R.id.txt3 :

                intent.putExtra("position", 1);
                startActivity(intent);
                break;
        }
    }

}
