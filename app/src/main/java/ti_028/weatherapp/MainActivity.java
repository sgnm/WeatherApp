package ti_028.weatherapp;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {

    private ColorDrawable actionbarBg;
    private RequestQueue reqQueue;
    private static final String weatherUrl = "http://api.openweathermap.org/data/2.5/forecast?id=1850147&units=metric";

    @InjectView(R.id.textRain) TextView textRain;
    @InjectView(R.id.textPlace) TextView textPlace;
    @InjectView(R.id.textMintemp) TextView textMintemp;
    @InjectView(R.id.textMaxtemp) TextView textMaxtemp;
    @InjectView(R.id.imageView) ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        imageView.setImageResource(R.drawable.weather_bg);
        actionbarBg = new ColorDrawable(Color.parseColor("#ffffff"));
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionbarBg.setAlpha(50);
            actionBar.setBackgroundDrawable(actionbarBg);
            Log.d("BAR", "called setBg on actionBar");
            // ActionBarの高さを引いた値が、背景画像の高さになるので、Activityで設定し直す
            getWindow().getDecorView().setBackgroundResource(R.drawable.weather_bg);
        } else {
            Log.d("BAR", "failed to get actionBar");
        }
        getWeatherData();
    }

    private void getWeatherData(){
        reqQueue = Volley.newRequestQueue(this);
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject cityObj = response.getJSONObject("city");
                    String cityName = cityObj.getString("name");

                    JSONArray listArray = response.getJSONArray("list");
                    JSONObject obj = listArray.getJSONObject(0);
                    JSONObject rainObj = obj.getJSONObject("rain");
                    float rain = (float)rainObj.getDouble("3h");

                    JSONObject mainObj = obj.getJSONObject("main");
                    int temp_min = (int)Math.round(mainObj.getDouble("temp_min"));
                    int temp_max = (int)Math.round(mainObj.getDouble("temp_max"));

                    textRain.setText(String.valueOf(rain));
                    textPlace.setText(cityName);
                    textMintemp.setText("/" + temp_min + "°");
                    textMaxtemp.setText(temp_max + "°");

                    Log.d("temp", String.valueOf(temp_min));
                    Log.d("temp", String.valueOf(temp_max));
                    Log.d("cityName", cityName);
//                    Log.d("rain", String.valueOf(rainObj.getDouble("3h")));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onResponse", "error = " + error);
            }
        };

        JsonObjectRequest jsonReq = new JsonObjectRequest(weatherUrl, null, listener, errorListener);
        reqQueue.add(jsonReq);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
