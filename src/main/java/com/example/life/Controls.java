package com.example.life;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.*;
import java.util.Random;

public class Controls extends VBox {
    private Button startButton;
    private Button stopButton;
    private Button clearButton;
    private Button randomButton;

    private Label randomValueLabel;
    private Slider randomValueSlider;

    private Label totalCells;
    private Label activeCells;
    private Label activeCellsAmount;
    private Label generation;
    private GameOfLife game = null;

    private Label speedLabel;
    public Slider speedSlider;
    public Controls(){
        this.startButton = new Button("Start");
        this.stopButton = new Button("Stop");
        this.clearButton = new Button("Clear");

        this.randomButton = new Button("Randomize");
        this.randomValueSlider = new Slider(0, 1, 0.5);
        this.randomValueLabel = new Label("Value: ");

        randomValueSlider.setShowTickLabels(true);
        randomValueSlider.setShowTickMarks(true);
        randomValueSlider.setMajorTickUnit(0.1);
        randomValueSlider.setMinorTickCount(5);

        randomValueLabel.textProperty().bind(randomValueSlider.valueProperty().asString("%.2f"));

        HBox randomizerBox = new HBox(randomButton, randomValueLabel,randomValueSlider);

        startButton.setOnAction(event -> startGame());
        stopButton.setOnAction(event -> stopGame());
        clearButton.setOnAction(event -> clearGame());
        randomButton.setOnAction(event -> randomizeBoard(randomValueSlider.getValue()));

        HBox buttonBox = new HBox(startButton, stopButton, clearButton);

        totalCells = createStyledLabel("Total Cells: ");
        activeCells = createStyledLabel("Active Cells: ");
        activeCellsAmount = createStyledLabel("Active Cells Amount: ");
        generation = createStyledLabel("Generation: ");

        speedLabel = new Label("Speed value: ");
        speedSlider = new Slider(0.05, 0.5, 0.1);

        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);
        speedSlider.setMajorTickUnit(0.1);
        speedSlider.setMinorTickCount(5);

        speedLabel.textProperty().bind(speedSlider.valueProperty().asString("%.2f"));
        HBox speedBox = new HBox(speedLabel, speedSlider);

        VBox labelsBox = new VBox(totalCells, activeCells, activeCellsAmount, generation);

        getChildren().addAll(buttonBox, randomizerBox, speedBox, labelsBox);
    }
    public void randomizeBoard(Double value){
        game.randomizeBoard(value);
    }

    private Label createStyledLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;"); // Apply styling
        return label;
    }

    public void setGame(GameOfLife gameOfLife){
        this.game = gameOfLife;
    }

    public void updateStats(int totalCells, int activeCells, double activeCellsAmount, int generation){
        this.totalCells.setText("Total Cells: " + totalCells);
        this.activeCells.setText("Active Cells: " + activeCells);
        this.activeCellsAmount.setText("Active Cells Amount: " + String.format("%.2f%%", activeCellsAmount));
        this.generation.setText("Generation: " + generation);
    }

    private void startGame(){
        game.startGame();
    }

    private void stopGame(){
        game.stopGame();
    }
    private void clearGame(){
        game.clearBoard();
        this.generation.setText("Generation: 0");
    }
}
