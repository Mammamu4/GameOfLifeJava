package com.example.life;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveManager{
    public static final String SAVE_FOLDER = "grid_data";
    public static ObservableList<String> getGridDataFiles() {
        List<String> fileList = new ArrayList<>();
        File folder = new File("grid_data"); // Assuming "grid_data" is the folder name
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName().replace(".txt", "");
                    fileList.add(fileName);
                }
            }
        }
        return FXCollections.observableArrayList(fileList);
    }
    public static void saveGrid(Cell[][] grid, String fileName) {
        String filePath = SAVE_FOLDER + File.separator + fileName;
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            int rows = grid.length;
            int cols = grid[0].length;
            writer.println(rows + " " + cols); // Write grid dimensions
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    writer.print(grid[i][j].isAlive() ? "1" : "0"); // Write cell state
                }
                writer.println(); // Move to the next line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load grid from a file
    public static void loadGrid(Cell[][] board, boolean[][] next, String fileName) {
        String filePath = SAVE_FOLDER + File.separator + fileName;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String[] dimensions = reader.readLine().split(" ");
            int rows = Integer.parseInt(dimensions[0]);
            int cols = Integer.parseInt(dimensions[1]);
            for (int i = 0; i < rows; i++) {
                String line = reader.readLine();
                for (int j = 0; j < cols; j++) {
                    boolean alive = line.charAt(j) == '1';
                    board[i][j].setAlive(alive);
                    next[i][j] = alive;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
