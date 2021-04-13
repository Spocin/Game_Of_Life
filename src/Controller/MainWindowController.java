package Controller;

import Cell.MyButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainWindowController {

    @FXML
    FlowPane flowPane;

    @FXML
    Button startButton, stopButton, clearButton;

    int width;
    int height;

    MyButton[][] buttonsArray;

    boolean working;
    ExecutorService executor;


    public void initialize() {
        this.executor = Executors.newFixedThreadPool(1);

        fillPane(37,25);

        setupStartButton();
        setupStopButton();
        setupClearButton();
    }


    private void fillPane (int width, int height) {

        this.width = width;
        this.height = height;

        buttonsArray = new MyButton[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                var button = new MyButton();
                buttonsArray[i][j] = button;
                flowPane.getChildren().add(button);
            }
        }
    }

    private void setupStartButton () {

        startButton.setDisable(false);

        startButton.setOnAction(event -> {

            startButton.setDisable(true);
            stopButton.setDisable(false);
            clearButton.setDisable(true);

            working = true;
            executor.submit(task);
        });
    }

    private void setupStopButton () {

        stopButton.setDisable(true);

        stopButton.setOnAction(event -> {

            stopButton.setDisable(true);
            startButton.setDisable(false);
            clearButton.setDisable(false);

            working = false;
        });
    }

    public void setupClearButton() {

        clearButton.setDisable(false);

        clearButton.setOnAction(event -> {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    buttonsArray[i][j].setDeactivated();
                }
            }
        });
    }


    private void setupState() {

        boolean[][] nextStateArray = new boolean[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                nextStateArray[i][j] = countState(i, j);
            }
        }

        for (int i = 0; i < nextStateArray.length; i++) {
            for (int j = 0; j < nextStateArray[i].length; j++) {

                if (nextStateArray[i][j]) {
                    buttonsArray[i][j].setActivated();

                } else {
                    buttonsArray[i][j].setDeactivated();
                }
            }
        }
    }

    private boolean countState(int i, int j) {

        int count = 0;

        //1
        if (i != 0 && j != 0) {
            if (buttonsArray[i-1][j-1].isActivated()) {
                count++;
            }
        }

        //2
        if (i != 0) {
            if (buttonsArray[i-1][j].isActivated()) {
                count++;
            }
        }

        //3
        if (i != 0 && j != width-1) {
            if (buttonsArray[i-1][j+1].isActivated()) {
                count++;
            }
        }

        //4
        if (j != 0) {
            if (buttonsArray[i][j-1].isActivated()) {
                count++;
            }
        }

        //6
        if (j != width-1) {
            if (buttonsArray[i][j+1].isActivated()) {
                count++;
            }
        }

        //7
        if (i != height-1 && j != 0) {
            if (buttonsArray[i+1][j-1].isActivated()) {
                count++;
            }
        }

        //8
        if (i != height-1) {
            if (buttonsArray[i+1][j].isActivated()) {
                count++;
            }
        }

        //9
        if (i != height-1 && j != width-1) {
            if (buttonsArray[i+1][j+1].isActivated()) {
                count++;
            }
        }

        if (buttonsArray[i][j].isActivated()) {
            return count == 2 || count == 3;
        } else {
            return count == 3;
        }
    }


    public void shutdown() {
        executor.shutdownNow();
    }

    Runnable task = () -> {
        while (working) {
            setupState();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                working = false;
            }
        }
    };
}