package Cell;

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


}
