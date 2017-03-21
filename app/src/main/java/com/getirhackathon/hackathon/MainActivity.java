package com.getirhackathon.hackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    AsyncHttpClient httpClient;
    JSONObject gelenJSON;
    JSONArray cizilecekJSON;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        httpClient = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("email","katilimciMail");
        params.put("name","katilimciName");
        params.put("gsm","katilimciGsm");
        httpClient.post("https://getir-bitaksi-hackathon.herokuapp.com/getElements", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("MainActivity", "Başarısız");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("MainActivity", responseString);
                try {

                    gelenJSON = new JSONObject(responseString);
                    cizilecekJSON = gelenJSON.getJSONArray("elements");
                    setContentView(new DrawerCanvas(getApplicationContext(),cizilecekJSON));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }


}
