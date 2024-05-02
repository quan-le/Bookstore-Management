package Theory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class a4_Menu extends Application
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
        //----------------------------------------------------
        //menu's component include
        //Menu Bar      ->
        //Menu Object   ->
        //Menu Item

        //---Create Menu Bar
        MenuBar main_menu = new MenuBar();

        //---Create Menu Object
        Menu File = new Menu("File");
        Menu Edit = new Menu("Edit");
        Menu Source = new Menu("Source");


        //---Create Menu Item for Menu Object
        MenuItem New = new MenuItem("New");
        MenuItem Open = new MenuItem("Open");
        MenuItem Save = new MenuItem("Save");

        //---Link Menu Object into Menu Bar
        main_menu.getMenus().addAll(File, Edit, Source);
        //---Link Menu Item into Menu Object
        File.getItems().add(New);
        File.getItems().add(Open);
        File.getItems().add(Save);

        //Adding a drop-down menu item by consider menu item as a separate distinct menu
        Menu dropDownMenu = new Menu("Drop down Menu");              //instead of defining it as menu item we consider it as a new menu
        File.getItems().add(dropDownMenu);                              //Linking a Menu Object to another Menu Obj to create drop down menu
            //define and link menu item for it
        MenuItem one = new MenuItem("1");
        MenuItem two = new MenuItem("2");
        dropDownMenu.getItems().add(one);
        dropDownMenu.getItems().add(two);


        //Add menu bar to layout
        BorderPane root = new BorderPane();
        root.setTop(main_menu);

        //add layout to Scene, a scene takes input layout
        sc = new Scene(root);

        //add Scene to Stage
        stage.setScene(sc);
        stage.setTitle("Click me Program");     // set title for stage
        stage.setWidth(1200);
        stage.setHeight(500);
        stage.show();                           // Important: display the stage

    }
}
