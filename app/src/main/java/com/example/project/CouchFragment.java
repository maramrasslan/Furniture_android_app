package com.example.project;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
//import android.widget.Toast;


public class CouchFragment extends Fragment {

    ImageView imageView;
    Button btn;
//    Button

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_couch,container,false);

        //find imageView by id
        imageView = (ImageView) view.findViewById(R.id.cart);
        btn = (Button) view.findViewById(R.id.logout_couch_btn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });

        //set onclick listener on imageView
        imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //startActivity( new Intent(WardrobeFragment.this, CartActivity.class));
                Intent intent= new Intent(CouchFragment.this.getActivity(),CheckOut.class);
                CouchFragment.this.startActivity(intent);
                //Toast.makeText(getActivity(), "ImageView clicked" , Toast.LENGTH_SHORT).show();
            }
        });

//        addToCart.

        // Inflate the layout for this fragment
        return view;
        //return inflater.inflate(R.layout.fragment_couch, container, false);
    }
}