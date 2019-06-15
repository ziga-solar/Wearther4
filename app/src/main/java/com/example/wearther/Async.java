package com.example.wearther;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class Async  extends AsyncTask<String,Void,String>
    {
        boolean jsonRead = false;
        JSONObject jsonObject;

        public JSONObject getJsonObject() {
            return jsonObject;
        }

        public void setJsonObject(JSONObject jsonObject) {
            this.jsonObject = jsonObject;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub

        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            setJsonObject(getData(params[0]));
            return null;
        }

        public JSONObject getData(String loc)
        {
            HttpClient httpclient = new DefaultHttpClient();
            httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
            HttpGet request = new HttpGet("https://api.apixu.com/v1/current.json?key=16e1790e5c5348fda3d194549191406&q="+loc);
            JSONObject json = null;
            try
            {
                HttpResponse response = httpclient.execute(request);
                HttpEntity resEntity = response.getEntity();
                String _response= EntityUtils.toString(resEntity);
                Log.i("test", "getData: we failed chief"+_response);
                json = new JSONObject(_response);

            }catch(Exception e)
            {
                Log.i("test", "getData: we failed chief");
                e.printStackTrace();
            }

            httpclient.getConnectionManager().shutdown();
            return json;
        }

    }

