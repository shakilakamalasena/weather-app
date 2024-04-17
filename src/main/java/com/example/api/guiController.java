package com.example.api;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONObject;

public class guiController {
    private Stage stage;
    public String cityName = "";

    @FXML
    private AnchorPane backPane;

    @FXML
    private Button backbtn;

    @FXML
    private Button btn_close;

    @FXML
    private Button btn_close2;

    @FXML
    private Button btn_search;

    @FXML
    private Label datetime;

    @FXML
    private Label direction;

    @FXML
    private Label error;

    @FXML
    private AnchorPane frontPane;

    @FXML
    private Label humidity;

    @FXML
    private TextField loc;

    @FXML
    private Label outputTemp;

    @FXML
    private Label outputloc;

    @FXML
    private Label pressure;

    @FXML
    private Label weatherDesc;

    @FXML
    private Label windSpeed;

    @FXML
    void Search(ActionEvent event) {
        cityName = loc.getText();
        JsonConnect connection = new JsonConnect(cityName, error);

        try {
            JSONObject jsonObject = connection.Connect();
            if (jsonObject != null) {
                String test = jsonObject.getString("name");
                System.out.println(test);
                frontPane.setVisible(false);
                backPane.setVisible(true);
                showData();
            } else {
                error.setText("Location can't be found");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showData() {
        DataImplement dataImplement = new DataImplement(cityName);
//        outputloc.setText(cityName);
        outputTemp.setText(Integer.toString(dataImplement.Temparature()) + "°C");
        datetime.setText(dataImplement.dateTime());
        outputloc.setText(dataImplement.Location());
        weatherDesc.setText(dataImplement.WeatherDescription());
        humidity.setText(dataImplement.Humidity() + "%");
        windSpeed.setText(dataImplement.WindSpeed() + " m/s");
        pressure.setText(Double.toString(dataImplement.Pressure()) + " hpa");
        direction.setText(Double.toString(dataImplement.WindDirection()) + "°");
    }

    @FXML
    void closeWindow(ActionEvent event) {
        Stage stage = (Stage) btn_close.getScene().getWindow();
        stage.close();
    }

    @FXML
    void back(ActionEvent event) {
        frontPane.setVisible(true);
        backPane.setVisible(false);
        error.setText("");
    }
}
