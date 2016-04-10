/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.cams.ims;


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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ImsMenuController implements Initializable {

    @FXML
    Button addCat;

    @FXML
    AnchorPane parent;

    @FXML
    AnchorPane anchorpane;

    @FXML
    Button viewCat;

    @FXML
    HBox mainHBox;

    @FXML
    Button editItem;

    @FXML
    Button back;

    @FXML
    ImageView title;

    @FXML
    Button home;

    @FXML
    VBox vbox2;

    @FXML
    VBox vbox1;

    @FXML
    Button addItem;

    @FXML
    VBox vbox4;

    @FXML
    Pane smallPane;

    @FXML
    VBox vbox3;

    @FXML
    HBox smallHBox;

    @FXML
    Label main2;

    @FXML
    Label main1;

    @FXML
    Button deleteCat;

    @FXML
    Button deleteItem;

    @FXML
    AnchorPane anchor;

    @FXML
    ImageView logo;

    @FXML
    Button viewItem;

    @FXML
    Button editCat;
    
    @FXML
    private void handleAddItemAction(ActionEvent e) throws IOException {
        Parent homePage = FXMLLoader.load(ImsMenuController.class.getResource("../addResourceItem.fxml"));
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
    }
    
    @FXML
    private void handleBackAction(ActionEvent e) throws IOException {
        Parent homePage = FXMLLoader.load(ImsMenuController.class.getResource("../main.fxml"));
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
    }
    
    @FXML
    private void handleHomeAction(ActionEvent e) throws IOException {
        Parent homePage = FXMLLoader.load(ImsMenuController.class.getResource("../main.fxml"));
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
    }
    
    @FXML
    private void handleSelectItemAction(ActionEvent e) throws IOException {
        Parent homePage = FXMLLoader.load(ImsMenuController.class.getResource("../selectResourceItem.fxml"));
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
    }
    
    @FXML
    private void handleViewAction(ActionEvent e) throws IOException {
        Parent homePage = FXMLLoader.load(ImsMenuController.class.getResource("../viewResourceItem.fxml"));
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert home != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert back != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert addItem != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert addCat != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert editItem != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert editCat != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert deleteItem != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert deleteCat != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert viewItem != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert viewCat != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
    }

}
