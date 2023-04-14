module com.example.snakeandladder {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.snakeandladder to javafx.fxml;
    exports com.example.snakeandladder;
}