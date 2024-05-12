package JavaFX_Theory;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class a7_SceneBuilderController implements Initializable {

    @FXML
    private FontAwesomeIconView bookIcon;

    @FXML
    private Button btLogin;

    @FXML
    private AnchorPane leftScene;

    @FXML
    private Label loginLabel;

    @FXML
    private AnchorPane mainScene;

    @FXML
    private PasswordField password;

    @FXML
    private AnchorPane rightScene;

    @FXML
    private FontAwesomeIconView userIcon;

    @FXML
    private TextField username;

    @FXML
    private Label welcomeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
