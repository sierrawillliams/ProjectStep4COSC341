package com.example.projectstep4_cosc341;


import static com.example.projectstep4_cosc341.MainActivity.LOBR;
import static com.example.projectstep4_cosc341.MainActivity.LOR;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class BeerReview extends AppCompatActivity {

    EditText BeerName;
    EditText BeerType;
    EditText Review;
    RatingBar Rating;
    ArrayList<bReview> beerReviewList;
    ImageButton save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_review);
        BeerName = (EditText) findViewById(R.id.BeerName);
        BeerType = (EditText) findViewById(R.id.BeerType);
        Review = (EditText) findViewById(R.id.Review);
        Rating= (RatingBar) findViewById(R.id.Rating);
        save = findViewById(R.id.Save);



    }
    public void Save(View view){
        BeerName = (EditText) findViewById(R.id.BeerName);
        BeerType = (EditText) findViewById(R.id.BeerType);
        Review = (EditText) findViewById(R.id.Review);
        Rating= (RatingBar) findViewById(R.id.Rating);

        String name=BeerName.getText().toString();
        String type=BeerType.getText().toString();
        String review=Review.getText().toString();
        float rating=Rating.getRating();

        if(BeerName.getText().toString().length()==0
                || BeerType.getText().toString().length()==0
                ||Review.getText().toString().length()==0 || Rating.getRating()==0)
        {
            Toast.makeText(this, "Please fill out all of the review", Toast.LENGTH_LONG).show();
        }
        else{
            //add beer name beer type and rating to data storage system
            LOBR.read(getApplicationContext());

            if(LOBR.getBeerReviewList()==null){
                beerReviewList=new ArrayList<bReview>();
                beerReviewList.add(new bReview(name,type,review,rating));
                LOBR= new BeerReviewList(beerReviewList);
                LOBR.writeToFile(LOBR,getApplicationContext());

                Toast.makeText(BeerReview.this, "Your review has been posted.", Toast.LENGTH_LONG).show();
                finish();
            }else{
                beerReviewList=LOBR.getBeerReviewList();
                beerReviewList.add(new bReview(name,type,review,rating));
                LOBR= new BeerReviewList(beerReviewList);
                LOBR.writeToFile(LOBR,getApplicationContext());

                Toast.makeText(BeerReview.this, "Your review has been posted.", Toast.LENGTH_LONG).show();
                finish();
            }

        }

    }
    public void Delete(View view){
        finish();

    }


}