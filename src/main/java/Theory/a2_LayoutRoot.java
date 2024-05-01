package Theory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.scene.control.Button;
import javafx.scene.control.*;

public class a2_LayoutRoot extends Application
{
    public static void main(String[] args)
    {
        launch(); //mandatory
    }

    public Button bt1;
    public Scene sc;
    @Override
    public void start(Stage stage) throws Exception
    {
        //-----------------Simple Component of GUI--------------------

        //adding node - controller
        bt1 = new Button("click me");

        //define layout
        //we can think of it as a way to organize all the node - controller for easier access

        HBox root = new HBox();                 // Horizontal Box: arrange horizontally in a single row
        VBox root1 = new VBox();                // Vertical Box: arrange horizontally in a vertical row
        StackPane root2 = new StackPane();      // Stack Pane: place content in a back-to-front single stack (overlap eachother)
        FlowPane root3 = new FlowPane();        // Flow Pane: place content in either Veritical or Horizontal flow (automatically based on width and height (adjust by user) of the window
        GridPane root4 = new GridPane();        // Grid Pane: flexible grid of rows and columns but you have to defined it position
                //grid pane have Hgap and Vgap allow putting between rows and columns
        BorderPane root5 = new BorderPane();    // Border Pane:  put it content nodes in top, bottom, right, left, center region
        root.getChildren().add(bt1);            // Add button to layout

        //add layout to Scene, scene take input layout
        sc = new Scene(root);

        //add Scene to Stage
        stage.setScene(sc);
        stage.setTitle("Click me Program");     // set title for stage
        stage.show();                           // Important: display the stage

    }
}
