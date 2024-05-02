package Theory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

public class a7_SceneBuilder extends Application {
    public static void main(String[] args) {
        launch(); //mandatory
    }
    public Scene sc;

    @Override
    public void start(Stage stage) throws Exception {
        //-----------------Load fxml file into Root--------------------
        //HBox root = new HBox();
        System.out.println(getClass().getResource("a7_SceneBuilder.fxml"));

        //Load using filestream
        //String address = "src/main/java/Theory/s.fxml";
        //InputStream fxmladdress = new FileInputStream(address);
        //FXMLLoader loader = new FXMLLoader();
        //Parent root = loader.load(fxmladdress);

        //typical way to load but may cause error
        //Parent root = FXMLLoader.load(getClass().getResource("a7_SceneBuilder.fxml"));

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("a7_SceneBuilder.fxml"));
        Parent root = fxmlLoader.load();

            //if the file is outside of this (theory package) we use "/filename.fxml"

        //add root layout into scene
        sc = new Scene(root);

        //add Scene to Stage
        stage.setScene(sc);
        stage.setTitle("Bookstore Management ");     // set title for stage
        stage.setWidth(1200);
        stage.setHeight(500);
        stage.show();
    }
}
