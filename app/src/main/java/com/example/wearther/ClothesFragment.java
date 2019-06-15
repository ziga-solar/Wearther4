package com.example.wearther;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

public class ClothesFragment extends Fragment {
    double temp;

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clothes, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle weatherData = ((WeartherActivity) getActivity()).sendToFrag();
        imageView1 = (ImageView)getView().findViewById(R.id.imageView1);
        imageView2 = (ImageView)getView().findViewById(R.id.imageView2);
        imageView3 = (ImageView)getView().findViewById(R.id.imageView3);
        imageView4 = (ImageView)getView().findViewById(R.id.imageView4);
        imageView5 = (ImageView)getView().findViewById(R.id.imageView5);
        imageView6 = (ImageView)getView().findViewById(R.id.imageView6);

        textView1 = (TextView)getView().findViewById(R.id.textView1);
        textView2 = (TextView)getView().findViewById(R.id.textView2);
        textView3 = (TextView)getView().findViewById(R.id.textView3);
        textView4 = (TextView)getView().findViewById(R.id.textView4);
        textView5 = (TextView)getView().findViewById(R.id.textView5);
        textView6 = (TextView)getView().findViewById(R.id.textView6);

        try {
            JSONObject curr = new JSONObject(weatherData.getString("json_curr"));
            JSONObject condi = new JSONObject(curr.getJSONObject("condition").toString());
            String condiText = condi.getString("text");
            temp = curr.getDouble("temp_c");

            if (condiText.contains("rain") || condiText.contains("drizzle") || condiText.contains("shower") || condiText.contains("snow")){

                imageView1.setImageResource(R.drawable.sweater);
                textView1.setText("Sweater");

                imageView2.setImageResource(R.drawable.shirt_2);
                textView2.setText("T-shirt");

                imageView3.setImageResource(R.drawable.jeans);
                textView3.setText("Jeans");

                imageView4.setImageResource(R.drawable.panties);
                textView4.setText("Underwear");

                imageView5.setImageResource(R.drawable.shoe);
                textView5.setText("Shoes and socks");

                imageView6.setImageResource(R.drawable.poncho);
                textView6.setText("Raincoat");
            }
            else{
                if (temp > 15.0){
                    imageView1.setImageResource(R.drawable.shirt_2);
                    textView1.setText("T-shirt");

                    imageView2.setImageResource(R.drawable.swimsuit_1);
                    textView2.setText("Shorts");

                    imageView3.setImageResource(R.drawable.panties);
                    textView3.setText("Underwear");

                    imageView4.setImageResource(R.drawable.shoe);
                    textView4.setText("Shoes and socks");
                }
                else {

                    imageView1.setImageResource(R.drawable.sweater);
                    textView1.setText("Sweater");

                    imageView2.setImageResource(R.drawable.shirt_2);
                    textView2.setText("T-shirt");

                    imageView3.setImageResource(R.drawable.jeans);
                    textView3.setText("Jeans");

                    imageView4.setImageResource(R.drawable.panties);
                    textView4.setText("Underwear");

                    imageView5.setImageResource(R.drawable.shoe);
                    textView5.setText("Shoes and socks");
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
