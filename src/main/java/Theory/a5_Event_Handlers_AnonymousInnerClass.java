package Theory;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class a5_Event_Handlers_AnonymousInnerClass extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public Button bt1;
    public Scene sc;
    @Override
    public void start(Stage stage) throws Exception
    {
        //-----------------------------------Action handler for Button + Label----------------------------
        bt1 = new Button("click me");
        Label lb1 = new Label("");
        //adding action for the button
        bt1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                lb1.setText("Button is clicked");
            }
        });
        lb1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                lb1.setText("Label is clicked");
            }
        });


        //-----------------------get input from text field and password field------------------------------
        TextField txtField = new TextField();
        PasswordField pwdField = new PasswordField();
        Label lb2 = new Label("Your text field is: ");
        Label lb3 = new Label("Your password field is: ");
        Button bt2 = new Button("Get text field");
        bt2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lb2.setText(txtField.getText());
            }
        });
        Button bt3 = new Button("Get text field");
        bt3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lb3.setText(pwdField.getText());
            }
        });

        //Layout
        VBox root = new VBox();
        root.getChildren().addAll(bt1, lb1);            //add button to layout
        root.getChildren().addAll(txtField, pwdField, lb2,bt2, lb3, bt3);

        //add layout to Scene, the scene takes input layout
        sc = new Scene(root);

        //add Scene to Stage
        stage.setScene(sc);
        stage.setTitle("Click me Program");     // set title for stage
        stage.setWidth(1200);
        stage.setHeight(500);
        stage.show();                           // Important: display the stage

    }
}
