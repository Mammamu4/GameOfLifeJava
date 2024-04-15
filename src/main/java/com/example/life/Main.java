package com.example.life;

import com.example.life.GameOfLife;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        GameOfLife gameOfLife = new GameOfLife(100, 100, 5);
        Controls controls = new Controls();
        gameOfLife.setControls(controls);
        controls.setGame(gameOfLife);

        BorderPane root = new BorderPane();
        root.setCenter(gameOfLife);
        root.setBottom(controls);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game of Life");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
