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
        bt1 = new Button("click me bt1");
        Button bt2 = new Button("click me bt2");
        Button bt3 = new Button("click me bt3");
        Button bt4 = new Button("click me bt4");


        //define layout
        //we can think of it as a way to organize all the node - controller for easier access

        HBox root = new HBox();                 // Horizontal Box: arrange horizontally in a single row
        VBox root1 = new VBox();                // Vertical Box: arrange horizontally in a vertical row
            root.getChildren().add(bt1);        // Add button to layout
            root.getChildren().add(bt3);
            root.getChildren().add(bt4);
            root.getChildren().add(bt2);
            root.getChildren().addAll(bt1,bt2,bt3,bt4);
            root.setSpacing(20);                // Add space bewtween them (optimal for HBox Vbox)

        StackPane root2 = new StackPane();      // Stack Pane: place content in a back-to-front single stack (overlap eachother)
        FlowPane root3 = new FlowPane();        // Flow Pane: place content in either Veritical or Horizontal flow (automatically based on width and height (adjust by user) of the window
        GridPane root4 = new GridPane();        // Grid Pane: flexible grid of rows and columns but you have to defined it position
                //grid pane have Hgap and Vgap allow putting between rows and columns
                root4.add(bt1, 0, 0);
                root4.add(bt2, 1, 1);
                root4.add(bt3, 2, 2);
                root4.setHgap(20);
                root4.setVgap(20);
                root4.setGridLinesVisible(true);

        BorderPane root5 = new BorderPane();    // Border Pane:  put it content nodes in top, bottom, right, left, center region
                root5.setBottom(bt1);
                root5.setTop(bt2);
                root5.setCenter(bt3);

        //add layout to Scene, scene take input layout
        sc = new Scene(root);


        //add Scene to Stage
        stage.setScene(sc);
        stage.setTitle("Click me Program");     // set title for stage
        stage.show();                           // Important: display the stage

    }
}
