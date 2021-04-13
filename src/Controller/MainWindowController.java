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
    Button startButton, stopButton;

    MyButton[][] buttonsArray;
    boolean working;

    ExecutorService executor;

    public void initialize() {
        this.executor = Executors.newFixedThreadPool(1);

        fillPane(38,25);

        setupStartButton();
        setupStopButton();
    }

    private void fillPane (int width, int height) {

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

            working = true;

            Runnable task = () -> {
                while (working) {
                    //setupState();
                    //commitChanges();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        working = false;
                    }
                }
            };

            executor.submit(task);
        });
    }

    private void setupStopButton () {

        stopButton.setDisable(true);

        stopButton.setOnAction(event -> {

            stopButton.setDisable(true);
            startButton.setDisable(false);

            working = false;
        });
    }

    private void setupState() {
        //TODO
    }

    private void commitChanges() {
        //TODO
    }

    public void shutdown() {
        executor.shutdownNow();
    }
}