package com.example.tap_ex3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

public class Fragment1 extends Fragment {

    public Fragment1() {
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
        View fragView = inflater.inflate(R.layout.fragment_1, container, false);
        RadioButton frag1_btnDog = fragView.findViewById(R.id.frag1_btnDog);
        RadioButton  frag1_btnCat = fragView.findViewById(R.id. frag1_btnCat);
        RadioButton frag1_btnRabbit = fragView.findViewById(R.id.frag1_btnRabbit);
        final ImageView fragIvPet = fragView.findViewById(R.id.fragIvPet);
        frag1_btnDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragIvPet.setImageResource(R.drawable.dog);
            }
        });

        frag1_btnCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragIvPet.setImageResource(R.drawable.cat);
            }
        });
        frag1_btnRabbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          fragIvPet.setImageResource(R.drawable.rabbit);
            }
        });
        return fragView;
    }
}