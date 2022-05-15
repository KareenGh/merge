package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Back;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane Pane1;

    @FXML
    private TextField PasswordButton;

    @FXML
    private TextField UserNameButton;

    @FXML
    void Back(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Pane1.getChildren().setAll(pane);
    }

    @FXML
    void loginButton(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'Login.fxml'.";
        assert loginButton != null : "fx:id=\"LoginClick\" was not injected: check your FXML file 'Login.fxml'.";
        assert Pane1 != null : "fx:id=\"Pane1\" was not injected: check your FXML file 'Login.fxml'.";
        assert PasswordButton != null : "fx:id=\"PasswordButton\" was not injected: check your FXML file 'Login.fxml'.";
        assert UserNameButton != null : "fx:id=\"UserNameButton\" was not injected: check your FXML file 'Login.fxml'.";

    }

}
