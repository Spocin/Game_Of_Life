import Controller.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Controller/MainWindow.fxml"));
        Parent root = loader.load();
        MainWindowController controller = loader.getController();

        primaryStage.setOnCloseRequest(event -> controller.shutdown());

        primaryStage.getIcons().add(new Image("Cell/logo.png"));
        primaryStage.setTitle("Game Of Life");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
