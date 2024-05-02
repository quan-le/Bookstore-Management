package Theory;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class a6_Event_Handlers_UsingMethod extends Application implements EventHandler<ActionEvent> {

    public static void main(String[] args) {
        launch(args);
    }

    public Button bt1, bt2;
    public Scene sc;
    public Label lb1;
    @Override
    public void start(Stage stage) throws Exception
    {
        //-----------------------------------Action handler for Button + Label----------------------------
        bt1 = new Button("click me");
        lb1 = new Label(" ");
        //adding action for the button
        bt1.setOnAction(this);
        lb1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                lb1.setText("Label is clicked");
            }
        });
        lb1.setOnMouseMoved(c -> lb1.setText("Label is hovered"));
        bt2 = new Button("Button 2");
        bt2.setOnAction(this::handle);
        //Layout
        VBox root = new VBox();
        root.getChildren().addAll(bt1, lb1, bt2);            //add button to layout

        //add layout to Scene, the scene takes input layout
        sc = new Scene(root);

        //add Scene to Stage
        stage.setScene(sc);
        stage.setTitle("Click me Program");     // set title for stage
        stage.setWidth(1200);
        stage.setHeight(500);
        stage.show();                           // Important: display the stage

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource() == bt1)             //getSource() : will determine which button we are using
        {
            lb1.setText("Button is clicked");
        }
        if(actionEvent.getSource()== bt2)
        {
            bt2.setText("Button 2 is cliked");
        }
    }
}
