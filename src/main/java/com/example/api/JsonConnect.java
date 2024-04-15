package com.example.api;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class JsonConnect {
    private String loc;
    private Label error;
    //private ActionEvent event;

    public JsonConnect(String loc, Label error) {
        this.loc = loc;
        this.error = error;
    }

    public JsonConnect(String loc) {
        this.loc = loc;
    }

    JSONObject Connect() {
        OkHttpClient client = new OkHttpClient();
        JSONObject jsonObject = null;

        String apikey = "63bf77eac9da48db6c89bd4382204104";

        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/weather?q=" + loc + "&appid=" + apikey)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String jsonData = response.body().string();
                jsonObject = new JSONObject(jsonData);
                //System.out.println(main);
            } else {
                error.setText("Not found");
            }
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
