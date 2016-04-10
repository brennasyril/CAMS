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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.cams.database.DatabaseController;

/**
 * FXML Controller class
 *
 * @author Brenna
 */
public class ViewCropController implements Initializable {

    @FXML
    private Label nameValue;

    @FXML
    private Label period;

    @FXML
    private Label code;

    @FXML
    private AnchorPane anchor2;

    @FXML
    private Button back;

    @FXML
    private Label main;

    @FXML
    private ImageView title;

    @FXML
    private Label periodValue;

    @FXML
    private Button home;

    @FXML
    private Label codeValue;

    @FXML
    private VBox vbox2;

    @FXML
    private VBox vbox1;

    @FXML
    private HBox hbox;

    @FXML
    private Label name;

    @FXML
    private ImageView logo;

    @FXML
    private Label familyValue;

    @FXML
    private Label family;
    
    private Crop crop; 
    
    void setCrop(Crop inCrop) throws IOException {
        DatabaseController db = new DatabaseController("cras"); 
        crop = inCrop; 
        codeValue.setText(crop.getCropCode());
        nameValue.setText(crop.getCropName());
        familyValue.setText(crop.getPlantFam());
        periodValue.setText(crop.getCropPer());
        
    }

    @FXML
    void handleBackAction(ActionEvent e) throws IOException {
        Parent homePage = FXMLLoader.load(ViewCropController.class.getResource("../crasMenu.fxml"));
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
    }

    @FXML
    void handleHomeAction(ActionEvent e) throws IOException {
        Parent homePage = FXMLLoader.load(ViewCropController.class.getResource("../main.fxml"));
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
