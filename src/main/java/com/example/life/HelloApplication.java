package com.example.life;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GameOfLife gameOfLife = new GameOfLife(150, 150, 5);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(gameOfLife);

        Scene scene = new Scene(stackPane);

        stage.setScene(scene);
        stage.setTitle("Game of Life");
        stage.show();
    }
}
