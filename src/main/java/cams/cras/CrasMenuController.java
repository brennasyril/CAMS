/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.cams.cras;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.cams.database.DatabaseController;
import main.java.cams.ims.ImsMenuController;
import main.java.cams.ims.ResourceItemInquiryController;
import main.java.cams.ims.ViewResourceItemController;

/**
 * FXML Controller class
 *
 * @author Brenna
 */
public class CrasMenuController implements Initializable {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private HBox mainHBox;

    @FXML
    private Button back;

    @FXML
    private Button viewCrop;

    @FXML
    private Button viewCropPer;

    @FXML
    private Button viewCond;

    @FXML
    private ImageView title;

    @FXML
    private Button editCrop;

    @FXML
    private Button editCropPer;

    @FXML
    private Button editCond;

    @FXML
    private Button home;

    @FXML
    private VBox vbox2;

    @FXML
    private VBox vbox1;

    @FXML
    private VBox vbox4;

    @FXML
    private Button addCrop;

    @FXML
    private Button addCropPer;

    @FXML
    private Button addCond;

    @FXML
    private HBox smallHBox;

    @FXML
    private Label main2;

    @FXML
    private Label main1;

    @FXML
    private AnchorPane anchor;

    @FXML
    private ImageView logo;

    @FXML
    void handleAddCropAction(ActionEvent e) throws IOException {
        Parent homePage = FXMLLoader.load(CrasMenuController.class.getResource("../cropAdd.fxml"));
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
    }

    @FXML
    void handleAddCropPeriodAction(ActionEvent e) throws IOException {
        Parent homePage = FXMLLoader.load(CrasMenuController.class.getResource("../addCropPer.fxml"));
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
    }

    @FXML
    void handleAddCondAction(ActionEvent e) throws IOException {
        Parent homePage = FXMLLoader.load(CrasMenuController.class.getResource("../addCond.fxml"));
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
    }

    @FXML
    void handleSelectCropAction(ActionEvent event) {

    }

    @FXML
    void handleSelectCropPeriodAction(ActionEvent event) {

    }

    @FXML
    void handleSelectCondAction(ActionEvent event) {

    }

    @FXML
    void handleViewCropAction(ActionEvent event) throws IOException {
        DatabaseController db = new DatabaseController("cras");
        String query = "Select * from `cra_crop_br` where `CRACropCode` = 'TOMAT01'";
        ArrayList<String[]> items = new ArrayList<String[]>();
        items = db.getQueryResult(query);
        Crop crop = null; 
        for (int i = 0; i < items.size(); i++) {
            String code = items.get(i)[0]; 
            String name = items.get(i)[1]; 
            String per = items.get(i)[2]; 
            String fam = items.get(i)[3]; 
            crop = new Crop(code, name, per, fam); 
        }
        
        
        FXMLLoader fxmlloader = new FXMLLoader(ViewResourceItemController.class.getResource("../viewCrop.fxml"));
        Parent homePage = (Parent) fxmlloader.load();
        ViewCropController newController = fxmlloader.<ViewCropController>getController();
        newController.setCrop(crop);
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
        oldStage.show();
    }

    @FXML
    void handleViewCropPeriodAction(ActionEvent event) {

    }

    @FXML
    void handleViewCondAction(ActionEvent event) {

    }

    @FXML
    void handleBackAction(ActionEvent e) throws IOException {
        Parent homePage = FXMLLoader.load(CrasMenuController.class.getResource("../main.fxml"));
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
    }

    @FXML
    void handleHomeAction(ActionEvent e) throws IOException {
        Parent homePage = FXMLLoader.load(CrasMenuController.class.getResource("../main.fxml"));
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert viewCrop != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert editCrop != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";

        assert viewCropPer != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert editCropPer != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";

        assert viewCond != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert editCond != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
    }

}
