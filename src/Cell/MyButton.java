package Cell;

import javafx.application.Platform;

public class MyButton extends javafx.scene.control.Button {

    private boolean activated;

    private final String offStyle = "-fx-background-color: #e3e3e3";
    private final String onStyle = "-fx-background-color: #737373";

    public MyButton () {
        this.activated = false;
        setStyle(offStyle);

        setOnAction(event -> {
            if (activated) {
                setStyle(offStyle);
                this.activated = false;
            } else {
                setStyle(onStyle);
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
            setStyle(onStyle);
        });
    }

    public void setDeactivated() {
        Platform.runLater(() -> {
            this.activated = false;
            setStyle(offStyle);
        });
    }
}
