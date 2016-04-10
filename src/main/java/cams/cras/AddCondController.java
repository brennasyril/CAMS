/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.cams.cras;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Brenna
 */
public class AddCondController implements Initializable {

    @FXML
    private TextField codeField;

    @FXML
    private Button addCond;

    @FXML
    private TextField descField;

    @FXML
    private Button back;

    @FXML
    private TextField nameField;

    @FXML
    private Button home;

    @FXML
    void handleBackAction(ActionEvent e) throws IOException {
        Parent homePage = FXMLLoader.load(AddCondController.class.getResource("../main.fxml"));
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
    }

    @FXML
    void handleHomeAction(ActionEvent e) throws IOException {
        Parent homePage = FXMLLoader.load(AddCondController.class.getResource("../crasMenu.fxml"));
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
    }

    @FXML
    void handleAddAction(ActionEvent e) {
        String code, desc, name; 
        
        code = codeField.getText().trim();
        name = nameField.getText().trim();
        desc = descField.getText().trim();
        
        Condition newCond = new Condition(code, name, desc); 
        newCond.addCondition();
        Alert addedAlert = new Alert(Alert.AlertType.INFORMATION);
        addedAlert.setTitle("Confirmation");
        addedAlert.setHeaderText("Congratulations");
        addedAlert.setContentText("Your new condition has been added");
        addedAlert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
