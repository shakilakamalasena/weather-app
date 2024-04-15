module com.example.api {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires okhttp3;



    opens com.example.api to javafx.fxml;
    exports com.example.api;
}