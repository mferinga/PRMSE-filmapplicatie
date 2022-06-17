package com.joost.filmapplicatie.Presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import java.util.Locale;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.joost.filmapplicatie.R;

public class SettingsActivity extends AppCompatActivity {
    private UiModeManager uiModeManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setTitle("Settings");
        uiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);
    }

    public void saveSettings(View view) {

        Switch darkModeSwitch = findViewById(R.id.dark_mode_switch);

        boolean isDarkModeChecked = darkModeSwitch.isChecked();
        Log.i("SettingsActivity", "Language switch = " + isDarkModeChecked);

        if (isDarkModeChecked){
            uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
        } else {
            uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_NO);
        }


        Switch languageSwitch = findViewById(R.id.languageSwitch);

        languageSwitch.isChecked();

        boolean isLanguageChecked = languageSwitch.isChecked();
        Log.i("SettingsActivity", "Language switch = " + isLanguageChecked);

        if (isLanguageChecked){
            setLocale("nl");
        } else {
            setLocale("en");
        }


        Intent intent = new Intent(this, MovieListActivity.class);
        startActivity(intent);
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MovieListActivity.class);
        finish();
        startActivity(refresh);
    }
}