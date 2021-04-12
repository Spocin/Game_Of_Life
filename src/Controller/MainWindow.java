package Controller;

import Cell.MyButton;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;

public class MainWindow {

    @FXML
    FlowPane flowPane;

    List<MyButton> listOfButtons = new ArrayList<>();

    public void initialize() {

        fillPane(38,25);
    }

    private void fillPane (int width, int height) {

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                listOfButtons.add(new MyButton());
            }
        }

        flowPane.getChildren().addAll(listOfButtons);
    }
}