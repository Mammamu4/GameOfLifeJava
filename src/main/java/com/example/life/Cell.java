package com.example.life;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Cell extends Rectangle {
    private boolean isAlive;
    private int age;
    private static Random random = new Random();
    private GameOfLife game;

    public int col;
    public int row;

    public Cell(int size){
        this.isAlive = false;
        setWidth(size);
        setHeight(size);
        paint();
        this.age = 0;
    }
    public Cell(int size, int row, int col, double randomValue, GameOfLife game){
        this.isAlive = random.nextDouble() < randomValue;
        this.row = row;
        this.col = col;
        this.game = game;

        setWidth(size);
        setHeight(size);
        setStroke(Color.BLACK);
        setStrokeWidth((double) size /20);
        paint();
        this.age = 0;

        setOnMousePressed(this::handleMousePressed);
        setOnMouseDragged(this::handleMouseDragged);
    }
    private void handleMousePressed(MouseEvent mouseEvent) {
        game.updateCell(this, !this.isAlive);
    }
    private void handleMouseDragged(MouseEvent mouseEvent) {
        setAlive(true);
    }

    public boolean isAlive(){
        return this.isAlive;
    }
    public void setAlive(boolean isAlive){
        this.isAlive = isAlive;
        if (isAlive){
            increaseAge();
        } else{
            resetAge();
        }
        paint();
    }
    public void toggle(){
        this.isAlive = !this.isAlive;
        paint();
    }
    public void increaseAge(){
        this.age++;
    }
    public void resetAge(){
        this.age = 0;
    }
    public int getAge(){
        return this.age;
    }
    public void paint(){
        this.setFill(this.isAlive ? Color.BLACK : Color.WHITE);
    }
}

