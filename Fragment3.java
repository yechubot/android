package com.example.tap_ex3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class Fragment3 extends Fragment {

    public Fragment3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragView = inflater.inflate(R.layout.fragment_3, container, false);
        final EditText height = fragView.findViewById(R.id.height);
        final EditText weight = fragView.findViewById(R.id.weight);
        Button bmi_btn = fragView.findViewById(R.id.bmi_btn);
        final TextView result = fragView.findViewById(R.id.result);

        bmi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double h = Double.parseDouble(height.getText().toString());
                double w = Double.parseDouble(weight.getText().toString());
                double standardW = (h-100)*0.9;
                if(w>= standardW){
                    result.setText("비만");
                }else if(w>=standardW-5){
                    result.setText("정상");
                }else {
                    result.setText("마름");
                }
            }
        });


        return fragView;
    }
}