package com.example.life;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.*;
import java.util.Random;

public class GameOfLife extends GridPane {
    private int rows;
    private int cols;
    private int squareSize;
    private int totalCells;

    private double speed;

    public Cell[][] board;
    public boolean[][] next;

    private int activeCells;
    private int generation;

    public Timeline timeline;
    private Controls controls = null;
    public GameOfLife(int rows, int cols, int squareSize){
        this.rows = rows;
        this.cols = cols;
        this.squareSize = squareSize;
        this.totalCells = rows * cols;
        this.speed = 0.05;
        this.generation = 0;

        setWidth(squareSize*rows);
        setHeight(squareSize*cols);

        this.board = new Cell[rows][cols];
        this.next = new boolean[rows][cols];

        this.activeCells = 0;

        initializeGrid();

        timeline = new Timeline(
                new KeyFrame(Duration.seconds(speed), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        nextGeneration();
                        controls.updateStats(getTotalCells(), getActiveCells(), activeCells/(double)(rows*cols) * 100, generation);
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
    }
    public void setSpeed(double speed){
        this.speed = speed;
    }
    public int getRows(){
        return this.rows;
    }
    public int getCols(){
       return this.cols;
    }
    public void setControls(Controls controls){
        this.controls = controls;
    }
    public void startGame(){
        timeline.play();
    }
    public void stopGame(){
        timeline.stop();
    }
    public int getActiveCells(){
        return this.activeCells;
    }
    public int getTotalCells(){
        return this.totalCells;
    }
    public void randomizeBoard(double randomValue){
        Random random = new Random();
        for (int row = 0; row < this.rows; row++){
            for (int col = 0; col < this.cols; col++){
                boolean isAlive = random.nextDouble() < randomValue;
                updateCell(board[row][col], isAlive);
            }
        }
    }
    public void updateCell(Cell cell, boolean alive){
        board[cell.row][cell.col].setAlive(alive);
        next[cell.row][cell.col] = alive;
    }
    public void clearBoard(){
        for (int row = 0; row < this.rows; row++){
            for (int col = 0; col < this.cols; col++){
                board[row][col].setAlive(false);
                next[row][col] = false;
            }
        }
        generation = 0;
    }
    private void initializeGrid(){
        for (int row = 0; row < this.rows; row++){
            for (int col = 0; col < this.cols; col++){
                Cell cell = new Cell(squareSize, row, col, 0.0, this);
                this.board[row][col] = cell;
                this.next[row][col] = cell.isAlive();
                activeCells += cell.isAlive() ? 1 : 0;
                add(cell, col, row);
            }
        }
    }
    public void nextGeneration(){
        activeCells = 0;
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[0].length; col++){
                Cell cell = board[row][col];
                int neighbors = countNeighbors(row, col);

                //Rules for next gen
                if (cell.isAlive() && (neighbors < 2 || neighbors > 3)){
                    next[row][col] = false;
                } else if (!cell.isAlive() && neighbors == 3){
                    next[row][col] = true;
                }
            }
        }

        for (int row = 0; row < next.length; row++) {
            for (int col = 0; col < next[0].length; col++) {
                boolean isAlive = next[row][col];
                board[row][col].setAlive(isAlive);
                if (isAlive){
                    activeCells += 1;
                }
            }
        }
        requestLayout();
        generation++;
    }
    private int countNeighbors(int row, int col){
        int sum = 0;
        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                int x = (row + i + rows) % rows;
                int y = (col + j + cols) % cols;
                if (board[x][y].isAlive()){
                    sum++;
                }
            }
        }
        if (board[row][col].isAlive()){
            sum--;
        }
        return sum;
    }

}