package com.example.wearther;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.wearther.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class WeartherActivity extends AppCompatActivity {
    JSONObject json;
    final String TAG = "MainWeather";
    Bundle weatherData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wearther);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        Intent i = getIntent();
        if( i.hasExtra("location_key"))
        {
            try {
                json = new JSONObject(Objects.requireNonNull(i.getStringExtra("json_key")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        weatherData = new Bundle();
        JSONObject loc = new JSONObject();
        JSONObject curr = new JSONObject();
        JSONObject condi = new JSONObject();

        try {
             loc = json.getJSONObject("location");
             curr = json.getJSONObject("current");
             condi = curr.getJSONObject("condition");

             weatherData.putString("json_loc",loc.toString());
             weatherData.putString("json_curr",curr.toString());
             weatherData.putString("json_condi",condi.toString());



        } catch (JSONException e) {
            Log.w(TAG, "onCreate: fuuuuck " + e );
            e.printStackTrace();
        }
    }
    public Bundle sendToFrag(){
        return weatherData;
    }


}