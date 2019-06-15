package com.example.wearther;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView location;
    String loc;
    Button bttn;
    JSONObject jsonObject;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        location = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        bttn = (Button) findViewById(R.id.mainButton);

        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(MainActivity.this, WeartherActivity.class);
                loc = location.getText().toString();
                String url = "http://api.apixu.com/v1/forecast.json?key=16e1790e5c5348fda3d194549191406&q="+loc;

                StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.i("yeet", "onResponse: "+jsonObject.toString());
                            setJsonObject(jsonObject);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jo = jsonArray.getJSONObject(i);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Anything you want
                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);




            }
        });
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        intent.putExtra("location_key", loc);
        intent.putExtra("json_key",jsonObject.toString());
        startActivity(intent);
    }
}








