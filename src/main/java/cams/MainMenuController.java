package main.java.cams;
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
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author GUS
 */
public class MainMenuController implements Initializable {

    @FXML
    Button ims;

    @FXML
    Button fms;

    @FXML
    Button cras;

    @FXML
    Button pas;

    @FXML
    SplitPane split;

    @FXML
    AnchorPane anchor;

    @FXML
    ImageView logo;

    @FXML
    ImageView title;

    @FXML
    Label main;

    @FXML
    Pane pane;
    @FXML
    VBox box;
    

    @FXML
    private void handleIMSAction(ActionEvent e) throws IOException {
         Parent homePage = FXMLLoader.load(MainMenuController.class.getResource("imsMenu.fxml"));
         Scene homepage = new Scene(homePage); 
         Stage oldStage  = (Stage) ((Node) e.getSource()).getScene().getWindow(); 
         oldStage.setScene(homepage);
    }
    
    @FXML
    private void handleCRASAction(ActionEvent e) throws IOException {
         Parent homePage = FXMLLoader.load(MainMenuController.class.getResource("crasMenu.fxml"));
         Scene homepage = new Scene(homePage); 
         Stage oldStage  = (Stage) ((Node) e.getSource()).getScene().getWindow(); 
         oldStage.setScene(homepage);
    }
   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert split != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert ims != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert anchor != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert logo != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert fms != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert pane != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert main != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert pas != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert cras != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert box != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert title != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
    }
}


