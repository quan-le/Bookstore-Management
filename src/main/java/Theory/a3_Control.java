package Theory;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
public class a3_Control extends Application
{
    public static void main(String[] args) {
        launch(); //mandatory
    }

    public Button bt1;
    public Scene sc;
    @Override
    public void start(Stage stage) throws IOException
    {
        //---------------------------------------------------NODE---------------------------------------------------
        //---------------------Control--------------------
        //Button
        bt1 = new Button("click me bt1");
        Button bt2 = new Button("click me bt2 and this is also a very long text.....................");
        bt2.setWrapText(true);
        bt2.setDisable(true);                           //the button is disabled and cannot be clicked
                //add image to button

        /*-----This part use for all control to add image into it,  basically a way to load image-----*/
        /*FileInputStream input = new FileInputStream("location_of_image.jpg");         //input of image         img = new Image(input);                                                       //create object image
        ImageView imgview = new ImageView((img));     */                                      //display object image
        /*-------------------------------------*/
        //Button bt3 = new Button("Enter your name ", imgview );                           //create button with img

        //--------------------------------------------------------------------------------------------

        //Radio Button --basically similar to multiple choice, can only choose 1 for each toggle group
        //usually we use VBox for this
        ToggleGroup tgroup = new ToggleGroup();
        RadioButton opt1 = new RadioButton("Option 1 Radiobutton");
        opt1.setToggleGroup(tgroup);
        RadioButton opt2 = new RadioButton("Option 2 Radiobutton");
        opt2.setToggleGroup(tgroup);
        RadioButton opt3 = new RadioButton("Option 3 Radiobutton");
        opt3.setToggleGroup(tgroup);
        //method for event hadler
        opt1.isSelected();
        opt1.getText();
        //-------------------------------------------------------------------------

        //Checkbox --can check as much as you want
        CheckBox CB_opt1 = new CheckBox("Option 1 CheckBox");
        CheckBox CB_opt2 = new CheckBox("Option 2 CheckBox");
        CheckBox CB_opt3 = new CheckBox("Option 3 CheckBox");
        //method for event hadler
        CB_opt1.isSelected();
        CB_opt1.getText();
        //--------------------------------------------------------------------------

        //Label --for adding text
        Label lb1 = new Label("Enter your name: Label");
        lb1.setText("Your name is: ");
        lb1.setTextAlignment(TextAlignment.CENTER);     //LEFT, RIGHT, CENTER, JUSTIFY
        lb1.setTextFill(Color.RED);                     //change color of a text
        lb1.setWrapText(true);                          //wrap text for long text
        lb1.setFont(new Font("Times New Roman", 32));
                //---setting image for label
        //FileInputStream input = new FileInputStream("location_of_image.jpg");             //input of image
        // img = new Image(input);                                                          //create object image
        //ImageView imgview = new ImageView((img));                                         //display object image
        //Label lb2 = new Label("Enter your name ", imgview );                              //cannot use settext() for imgview
        //-------------------------------------------------------------------------

        //Hyperlink
        Hyperlink link = new Hyperlink("google.com hyperlink");
        //-------------------------------------------------------------------------

        //ComboBox --drop down menu
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().add("Option 1 ComboBox");    //adding item to combo box
        comboBox.getItems().add("Option 2 ComboBox");
        comboBox.getItems().add("Option 3 ComboBox");
        comboBox.getItems().add("Option 4 ComboBox");
        //method for event hadler
        comboBox.getValue();
        //-------------------------------------------------------------------------

        //ListView
        ListView<String> lstView = new ListView<>();
        lstView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE /*or SINGLE*/);       //allow user to select mutiple item
        lstView.getItems().add("Option 1 ListView");
        lstView.getItems().add("Option 2 ListView");
        lstView.getItems().add("Option 3 ListView");
        //-------------------------------------------------------------------------

        //Text Field and Password Field
        TextField txtField = new TextField();
        txtField.setMaxSize(300,2);
        PasswordField pswField = new PasswordField();
        //-------------------------------------------------------------------------

        //Date Picker
        Label labelDP = new Label("Select your Date-Picker");
        DatePicker dp = new DatePicker();


        //----------------------------------------------layout---------------------------------
        FlowPane root = new FlowPane();
        root.getChildren().add(bt1);                                //add button to layout
        root.getChildren().add(bt2);
        root.getChildren().add(lb1);                                //add label to layout
        root.getChildren().add(lstView);                            //add list view to layout
        root.getChildren().addAll(txtField, pswField);              //add TextField, PasswordField to layout
        root.getChildren().addAll(labelDP, dp);
        root.setHgap(30);
        root.setVgap(30);
        VBox root1 = new VBox();
        root1.getChildren().addAll(opt1, opt2, opt3);               //adding all radio button
        root1.getChildren().addAll(CB_opt1, CB_opt2, CB_opt3);      //adding all check box

        //add layout to Scene, the scene takes input layout
        sc = new Scene(root);
        Scene sc1 = new Scene(root1);
        //add Scene to Stage
        stage.setScene(sc);
        //stage.setScene(sc1);
        stage.setTitle("Click me Program");     // set title for stage
        stage.setHeight(500);
        stage.setWidth(1200);
        stage.show();                           // Important: display the stage

    }
}
