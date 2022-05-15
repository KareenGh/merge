package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SignUpController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> AccountType;

    private String[] Accounts = {"Store Account" , "Chain Account: Shop in any Store" , "One year subscription: Pay 100nis and get 10% discount in every purchase over 50nis"};

    @FXML
    private Button Back;

    @FXML
    private TextField CreditCard;

    @FXML
    private TextField Email;

    @FXML
    private DatePicker ExpiryDate;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField ID;

    @FXML
    private TextField LastName;

    @FXML
    private AnchorPane Pane1;

    @FXML
    private TextField Password;

    @FXML
    private TextField PhoneNumber;

    @FXML
    private Button SignUp;

    @FXML
    private TextField UserName;

    @FXML
    void Back(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Pane1.getChildren().setAll(pane);
    }

    @FXML
    void ExpiryDate(ActionEvent event) {
        LocalDate Expiry_Date = ExpiryDate.getValue();
    }

    @FXML
    void SignUp(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert AccountType != null : "fx:id=\"AccountType\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert CreditCard != null : "fx:id=\"CreditCard\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert Email != null : "fx:id=\"Email\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert ExpiryDate != null : "fx:id=\"ExpiryDate\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert FirstName != null : "fx:id=\"FirstName\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert ID != null : "fx:id=\"ID\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert LastName != null : "fx:id=\"LastName\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert Password != null : "fx:id=\"Password\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert PhoneNumber != null : "fx:id=\"PhoneNumber\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert UserName != null : "fx:id=\"UserName\" was not injected: check your FXML file 'SignUp.fxml'.";

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        String SelectedAccount;
        AccountType.getItems().addAll(Accounts);
//        SelectedAccount = AccountType.getValue();
    }
}
