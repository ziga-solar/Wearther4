package com.example.wearther;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;


public class WeatherFragment extends Fragment {
    TextView locationTV;
    TextView humidityTV;
    TextView windTV;
    TextView timeTV;
    TextView tempTV;
    TextView condiTV;
    ImageView iconIV;

    final String TAG = "Weather";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        locationTV = (TextView) Objects.requireNonNull(getView()).findViewById(R.id.locationName);
        humidityTV = (TextView)getView().findViewById(R.id.weatherHumidityNumber);
        windTV = (TextView)getView().findViewById(R.id.weatherWindNumber);
        timeTV = (TextView)getView().findViewById(R.id.weatherTime);
        tempTV = (TextView)getView().findViewById(R.id.weatherTemp);
        iconIV = (ImageView)getView().findViewById(R.id.weatherImage);
        condiTV = (TextView)getView().findViewById(R.id.conditionText);

        Bundle weatherData = ((WeartherActivity) getActivity()).sendToFrag();

        Log.w(TAG, "onActivityCreated: "+weatherData.toString());
        JSONObject loc;
        JSONObject curr;
        JSONObject condi;
        double hum;
        double wind;
        String condition = "";

        try {
            Log.i(TAG, "onActivityCreated: "+weatherData.toString());
            loc = new JSONObject(Objects.requireNonNull(weatherData.getString("json_loc")));
            curr = new JSONObject(Objects.requireNonNull(weatherData.getString("json_curr")));
            condi = new JSONObject(Objects.requireNonNull(weatherData.getString("json_condi")));
            condition = condi.getString("text");
            hum = curr.getDouble("humidity");
            wind = curr.getDouble("wind_kph");
            locationTV.setText(loc.getString("name"));
            humidityTV.setText(hum+"");
            windTV.setText(wind+"");
            tempTV.setText(curr.getDouble("temp_c")+" C");
            timeTV.setText(loc.getString("localtime").split(" ")[1]);
            condiTV.setText(condition);

            switch (condition){
                case "Sunny":
                    Log.d(TAG, "onActivityCreated: "+condition);
                    iconIV.setImageResource(R.drawable.sun);
                    break;
                case "Clear":
                    iconIV.setImageResource(R.drawable.moon);
                    break;
                case "Partly cloudy":
                    iconIV.setImageResource(R.drawable.clouds_and_sun);
                    break;
                case "Cloudy":
                    iconIV.setImageResource(R.drawable.clouds);
                    break;
                case "Overcast":
                    iconIV.setImageResource(R.drawable.cloudy);
                    break;
                case "Mist":
                    iconIV.setImageResource(R.drawable.cloudy);
                    break;
                case "Patchy rain possible":
                    iconIV.setImageResource(R.drawable.rain);
                    break;
                case "Patchy snow possible":
                    iconIV.setImageResource(R.drawable.snow_and_sun);
                    break;
                case "Patchy sleet possible":
                    iconIV.setImageResource(R.drawable.snow_and_moon);
                    break;
                case "Patchy freezing drizzle possible":
                    iconIV.setImageResource(R.drawable.snow);
                    break;
                case "Thundery outbreaks possible":
                    iconIV.setImageResource(R.drawable.thunder);
                    break;
                case "Blowing snow":
                    iconIV.setImageResource(R.drawable.snow);
                    break;
                case "Blizzard":
                    iconIV.setImageResource(R.drawable.snow);
                    break;
                case "Fog":
                    iconIV.setImageResource(R.drawable.clouds);
                    break;
                case "Freezing fog":
                    iconIV.setImageResource(R.drawable.snow);
                    break;
                case "Patchy light drizzle":
                    iconIV.setImageResource(R.drawable.rain_and_sun);
                    break;
                case "Light drizzle":
                    iconIV.setImageResource(R.drawable.rain);
                    break;
                case "Freezing drizzle":
                    iconIV.setImageResource(R.drawable.snow);
                    break;
                case "Heavy freezing drizzle":
                    iconIV.setImageResource(R.drawable.snow);
                    break;
                case "Patchy light rain":
                    iconIV.setImageResource(R.drawable.rain);
                    break;
                case "Light rain":
                    iconIV.setImageResource(R.drawable.rain);
                    break;
                case "Moderate rain at times":
                    iconIV.setImageResource(R.drawable.rainstorm_and_sun);
                    break;
                case "Moderate rain":
                    iconIV.setImageResource(R.drawable.rainstorm);
                    break;
                case "Heavy rain at times":
                    iconIV.setImageResource(R.drawable.rainstorm_and_sun);
                    break;
                case "Heavy rain":
                    iconIV.setImageResource(R.drawable.rainstorm);
                    break;
                case "Light freezing rain":
                    iconIV.setImageResource(R.drawable.snow);
                    break;
                default:
                    iconIV.setImageResource(R.drawable.sun);
            }

        } catch (JSONException e) {
            Log.w(TAG, "onActivityCreated: fuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuck "+e);
            e.printStackTrace();
        }
    }
}
