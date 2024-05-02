package Theory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.scene.control.Button;
import javafx.scene.control.*;

public class a0_IntroductionGUI extends Application
{
    public static void main(String[] args)
    {
        launch(); //mandatory
    }
    /*
    * In general GUI in Java is divided into 2 import part
    * + GUI Design: (control added to layout, added to scene, added to stage)
    *       Control(Button, text bar, ...)  ->
    *       Layout or Root (how we arrange control) ->
    *       Scene  ->
    *       Stage (Basically window)
    * + Event:
    *
    * */

    public Button bt1;
    public Scene sc;
    @Override
    public void start(Stage stage) throws Exception
    {
        //-----------------Simple Component of GUI--------------------
        //adding node (in specific control: button)
                    //nodes can be understood as a tree kind of structure
                    /*                  Node
                    *   Shape 3d        Parent          Shape
                    *                   Region
                    *           (include Pane, Control)
                    *           Control: button, bar, ...
                    *           Pane: kind of like block of color(need to confirm)
                    * */
        bt1 = new Button("click me");

        //define layout
        HBox root = new HBox();
        root.getChildren().add(bt1);            //add button to layout

        //add layout to Scene, scene take input layout
        sc = new Scene(root);
        //sc = new Scene(root,400,400)

        //add Scene to Stage
        stage.setScene(sc);
        stage.setTitle("Click me Program");     // set title for stage
        stage.setWidth(1200);
        stage.setHeight(500);
        stage.show();                           // Important: display the stage

    }

}
