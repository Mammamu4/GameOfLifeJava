package com.example.life;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Cell extends Rectangle {
    private boolean isAlive;
    private int age;
    private static Random random = new Random();

    public Cell(int size){
        this.isAlive = random.nextDouble() < 0.25;
        setWidth(size);
        setHeight(size);
        paint();
        this.age = 0;

        setOnMousePressed(this::handleMousePressed);
        setOnMouseDragged(this::handleMouseDragged);
    }
    private void handleMousePressed(MouseEvent mouseEvent) {
        setAlive(true);
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

