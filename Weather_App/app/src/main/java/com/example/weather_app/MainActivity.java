package com.example.weather_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText etCity;
    Button btnSearch;
    TextView tvResult;
    ImageView weatherIcon;

    // ✅ Your WeatherAPI key
    String apiKey = "e5995ce1a0b34ff29ae85958250107";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCity = findViewById(R.id.etCity);
        btnSearch = findViewById(R.id.btnSearch);
        tvResult = findViewById(R.id.tvResult);
        weatherIcon = findViewById(R.id.weatherIcon);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = etCity.getText().toString().trim();
                if (!city.isEmpty()) {
                    getWeather(city);
                } else {
                    Toast.makeText(MainActivity.this, "Enter a city name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getWeather(String city) {
        String url = "https://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city;

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            JSONObject location = json.getJSONObject("location");
                            JSONObject current = json.getJSONObject("current");
                            JSONObject condition = current.getJSONObject("condition");

                            String name = location.getString("name");
                            double temp = current.getDouble("temp_c");
                            int humidity = current.getInt("humidity");
                            String weatherText = condition.getString("text");
                            String iconUrl = "https:" + condition.getString("icon");

                            String output = "City: " + name + "\n"
                                    + "Temperature: " + temp + " °C\n"
                                    + "Humidity: " + humidity + "%\n"
                                    + "Condition: " + weatherText;

                            tvResult.setText(output);
                            Glide.with(MainActivity.this).load(iconUrl).into(weatherIcon);

                        } catch (Exception e) {
                            e.printStackTrace();
                            tvResult.setText("Error parsing weather data");
                            Toast.makeText(MainActivity.this, "Parsing error", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        tvResult.setText("Error retrieving weather data.");
                        Toast.makeText(MainActivity.this, "Check city name or internet", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
