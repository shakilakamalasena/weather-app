package com.example.api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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

    @Override
    public String dateTime() {
        JsonConnect connect = new JsonConnect(location);
        long timeStamp = connect.Connect().getLong("dt");
        int timezoneOffset = connect.Connect().getInt("timezone");

        long timeStampInMillis = timeStamp * 1000;
        Date date = new Date(timeStampInMillis);
        long timezoneOffsetInMillis = timezoneOffset * 1000;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        if (timezoneOffset > 0) {
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+" + timezoneOffset / 3600));
        } else {
            sdf.setTimeZone(TimeZone.getTimeZone("GMT" + timezoneOffset / 3600));
        }
        return sdf.format(new Date(timeStampInMillis));
    }

    @Override
    public String Location() {
        JsonConnect connect = new JsonConnect(location);
        String outputLocation = "";
        try {
            String name = connect.Connect().getString("name");
            outputLocation = name;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputLocation;
    }

    @Override
    public Double Humidity() {
        JsonConnect connect = new JsonConnect(location);
        Double output = 0.0;
        try {
            Double humidity = connect.Connect().getJSONObject("main").getDouble("humidity");
            output = humidity;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;
    }

    @Override
    public Double WindSpeed() {
        JsonConnect connect = new JsonConnect(location);
        Double output = 0.0;
        try {
            Double windSpeed = connect.Connect().getJSONObject("wind").getDouble("speed");
            output = windSpeed;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;
    }

    @Override
    public Double Pressure() {
        JsonConnect connect = new JsonConnect(location);
        Double output = 0.0;
        try {
            Double pressure = connect.Connect().getJSONObject("main").getDouble("pressure");
            output = pressure;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;
    }

    @Override
    public Double WindDirection() {
        JsonConnect connect = new JsonConnect(location);
        Double output = 0.0;
        try {
            Double dir = connect.Connect().getJSONObject("wind").getDouble("deg");
            output = dir;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;
    }

//    @Override
//    public String Icon() {
//        JsonConnect connect = new JsonConnect(location);
//        String output = "";
//        try {
//            String icon = connect.Connect().getJSONObject("weather").getString("icon");
//            output = icon;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return output;
//    }
}
