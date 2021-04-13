package Cell;

import javafx.application.Platform;

public class MyButton extends javafx.scene.control.Button {

    private boolean activated;

    public MyButton () {
        this.activated = false;
        setStyle("-fx-background-color: #ffff");

        setOnAction(event -> {
            if (activated) {
                setStyle("-fx-background-color: #ffff");
                this.activated = false;
            } else {
                setStyle("-fx-background-color: #586161");
                this.activated = true;
            }
        });
    }

    public boolean isActivated () {
        return activated;
    }

    public void setActivated() {
        Platform.runLater(() -> {
            this.activated = true;
            setStyle("-fx-background-color: #586161");
        });
    }

    public void setDeactivated() {
        Platform.runLater(() -> {
            this.activated = false;
            setStyle("-fx-background-color: #ffff");
        });
    }
}
