package com.joost.filmapplicatie.Presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.joost.filmapplicatie.R;

public class HomescreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        getSupportActionBar().setTitle("Film applicatie");

        Log.d("HomescreenAcitivty", "Test 100: onCreate laad");
    }

    public void goToMain(View view){
        Log.d("HomescreenAcitivty", "Test 101: goToMain begin");

        Intent intent = new Intent(this, MovieListActivity.class);
        Log.d("HomescreenAcitivty", "Test 102: goToMain intent gemaakt");
        startActivity(intent);


    }

}