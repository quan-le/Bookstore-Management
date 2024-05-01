package Theory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
public class a1_Stage extends Application
{
    public static void main(String[] args)
    {
        launch();
    }

    public Button bt1;
    public Scene sc;
    @Override
    public void start(Stage stage) throws Exception
    {
        //-----------------Simple Component of GUI-----------
        //adding control (button)
        bt1 = new Button("click me");

        //define layout
        HBox root = new HBox();
        root.getChildren().add(bt1); //add button to layout

        //add layout to Scene, scene take input layout
        sc = new Scene(root);

        //add Scene to Stage
        stage.setScene(sc);
        //--------------------------------------------------------------

        //----------------------Stage method-------------------
        stage.setTitle("Click me Program");     // set title for stage
        stage.getTitle();                       // get title
        stage.setWidth(200);                    // set width of window (also have getHeight,getWidth method)
        stage.setHeight(200);                   // set height of window
        stage.setFullScreen(true);              // set fullscreen
        stage.isFullScreen();                   // return boolean show is the screen is fullscreen

        stage.show();                           // Important: display the stage
        stage.close();                          // Close the current stage (similar to hide())
        stage.hide();                           // hide current stage



    }
}
