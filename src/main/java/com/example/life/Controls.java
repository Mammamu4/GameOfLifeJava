package com.example.life;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Controls extends HBox {
    private Button startButton;
    private Button stopButton;
    private Label totalCells;
    private Label activeCells;
    private Label activeCellsAmount;
    private Label totalAge;
    private Label generation;
    private GameOfLife game = null;

    public Controls(){
        this.startButton = new Button("Start");
        this.stopButton = new Button("Stop");

        startButton.setOnAction(event -> startGame());
        stopButton.setOnAction(event -> stopGame());

        totalCells = createStyledLabel("Total Cells: ");
        activeCells = createStyledLabel("Active Cells: ");
        activeCellsAmount = createStyledLabel("Active Cells Amount: ");
        totalAge = createStyledLabel("Total Age: ");
        generation = createStyledLabel("Generation: ");

        getChildren().addAll(startButton, stopButton, totalCells, activeCells, activeCellsAmount, generation, totalAge);

        setSpacing(10); // Add spacing between buttons and labels
    }

    private Label createStyledLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;"); // Apply styling
        return label;
    }

    public void setGame(GameOfLife gameOfLife){
        this.game = gameOfLife;
    }

    public void updateStats(int totalCells, int activeCells, double activeCellsAmount, int totalAge, int generation){
        this.totalCells.setText("Total Cells: " + totalCells);
        this.activeCells.setText("Active Cells: " + activeCells);
        this.activeCellsAmount.setText("Active Cells Amount: " + String.format("%.2f%%", activeCellsAmount));
        this.totalAge.setText("Total Age: " + totalAge);
        this.generation.setText("Generation: " + generation);
    }

    private void startGame(){
        game.startGame();
    }

    private void stopGame(){
        game.stopGame();
    }
}
