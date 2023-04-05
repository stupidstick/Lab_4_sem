package controller;

import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import objects.GoldFish;
import objects.GuppyFish;

import java.net.URL;
import java.util.ResourceBundle;

public class IpChooserController implements Initializable {
    @FXML
    TextField ipField;
    @FXML
    TextField portField;
    Client client;
    @FXML
    private void ok(ActionEvent actionEvent){
        try{
            int port = Integer.parseInt(portField.getText());
            if (client.connect(ipField.getText(), port)){
                ((Node) actionEvent.getSource()).getScene().getWindow().hide();
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    @FXML
    private void cancel(ActionEvent actionEvent){
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
}
