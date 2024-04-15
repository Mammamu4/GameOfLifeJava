package com.example.life;

import com.example.life.GameOfLife;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {
    public GameOfLife gameOfLife;
    public Controls controls;

    private Button saveButton;
    private TextField saveField;
    private Button loadButton;
    private TextField loadField;
    @Override
    public void start(Stage primaryStage) {
        gameOfLife = new GameOfLife(60, 60, 15);
        controls = new Controls();
        gameOfLife.setControls(controls);
        controls.setGame(gameOfLife);

        this.saveButton = new Button("Save");
        this.loadButton = new Button("Load");
        this.saveField = new TextField();
        this.loadField = new TextField();

        saveButton.setOnAction(event -> handleSaveGrid());
        loadButton.setOnAction(event -> handleLoadGrid());

        HBox saveLoadButtons = new HBox(saveButton, saveField, loadButton, loadField);

        ListView<String> fileListView = new ListView<>();
        ObservableList<String> files = FXCollections.observableArrayList(SaveManager.getGridDataFiles());
        fileListView.setItems(files);

        BorderPane root = new BorderPane();
        root.setTop(saveLoadButtons);
        root.setCenter(gameOfLife);
        root.setRight(controls);
        root.setLeft(fileListView);

        Scene scene = new Scene(root);

        Image icon = new Image("nedladdning.png");
        primaryStage.getIcons().add(icon);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Game of Life");
        primaryStage.show();
    }
    private void handleSaveGrid() {
        SaveManager.saveGrid(gameOfLife.board, saveField.getText() + ".txt");
        // After saving, refresh the list of files in the ListView
        ListView<String> fileListView = (ListView<String>) ((BorderPane)saveButton.getParent().getParent()).getLeft();
        ObservableList<String> files = FXCollections.observableArrayList(SaveManager.getGridDataFiles());
        fileListView.setItems(files);
    }
    private void handleLoadGrid(){
        SaveManager.loadGrid(gameOfLife.board, gameOfLife.next, loadField.getText() + ".txt");
    }
    private void setFileList(){
        ListView<String> fileListView = new ListView<>();
        ObservableList<String> files = SaveManager.getGridDataFiles();
        fileListView.setItems(files);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
