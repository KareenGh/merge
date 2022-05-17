package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
//        Pane1.getChildren().setAll(pane);
        try {
            App.setRoot("HomePage");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void loginButton(ActionEvent event) {
        User User = new User(UserNameButton.getText(), PasswordButton.getText());
        try {
            SimpleClient.getClient().sendToServer(new Message("#LoginRequest", User));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
