package com.example.api;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataImplement implements Data {
    private String location;

    public DataImplement(String location) {
        this.location = location;
    }

    @Override
    public String WeatherDescription() {
        JsonConnect connect = new JsonConnect(location);
        String output = "";

        try {
            JSONArray weatherArray = connect.Connect().getJSONArray("weather");
            JSONObject weatherObject = weatherArray.getJSONObject(0);
            String description = weatherObject.getString("description");
            output = description;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;
    }

    @Override
    public int Temparature() {
        JsonConnect connect = new JsonConnect(location);
        int output = 0;

        try {
            double temperature = connect.Connect().getJSONObject("main").getDouble("temp");
            output = (int) Math.round(temperature - 273.15);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;
    }
}
