package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {
    private static Scene scene;

    public static Scene getScene() {
        return scene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

       Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getResource("text-highlighter.css").toExternalForm());
        primaryStage.setTitle("FileParser");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);

    }
}
