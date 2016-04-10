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
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Brenna
 */
public class ResourceItemInquiryController implements Initializable {
    
    @FXML
    private Label code;

    @FXML
    private AnchorPane anchor2;

    @FXML
    private Button back;

    @FXML
    private Label main;

    @FXML
    private Label reorder;

    @FXML
    private Label qoh;

    @FXML
    private ImageView title;

    @FXML
    private Label codeValue;

    @FXML
    private VBox vbox2;

    @FXML
    private VBox vbox1;

    @FXML
    private Label reorderValue;

    @FXML
    private Label current;

    @FXML
    private SplitPane split;

    @FXML
    private HBox hbox;

    @FXML
    private Label avg;

    @FXML
    private VBox vbox3;

    @FXML
    private Label upcValue;

    @FXML
    private ImageView logo;

    @FXML
    private Label lastSValue;

    @FXML
    private Label sku;

    @FXML
    private Label qohValue;

    @FXML
    private Label lastU;

    @FXML
    private Label avgValue;

    @FXML
    private Label nameValue;

    @FXML
    private Label lastS;

    @FXML
    private Label skuValue;

    @FXML
    private Label upc;

    @FXML
    private Label lastUValue;

    @FXML
    private Button home;

    @FXML
    private HBox smallHbox;

    @FXML
    private AnchorPane anchor;

    @FXML
    private Label name;

    @FXML
    private Label categoryValue;

    @FXML
    private Label acctValue;

    @FXML
    private Label category;

    @FXML
    private Label currentValue;

    @FXML
    private Label acct;

    @FXML
    private Label seq;

    @FXML
    private Label seqValue;

    private ResourceItem viewItem; 
    
    public void setItem(ResourceItem item) {
        viewItem = item; 
        codeValue.setText(viewItem.getItemCode());
        seqValue.setText("" + viewItem.getID());
        nameValue.setText(viewItem.getItemName());
        reorderValue.setText("" + viewItem.getReorderQuantity());
        upcValue.setText("" + viewItem.getUPCCode());
        lastSValue.setText("" + viewItem.getLastSellingPrice());
        qohValue.setText("" + viewItem.getQuantityOnHand());
        avgValue.setText("" + viewItem.getAverageUnitPrice()); 
        currentValue.setText("" + viewItem.getCurrentSellingPrice());
        acctValue.setText("" + viewItem.getAcctNum());
        categoryValue.setText(viewItem.getItemCategory());
        lastUValue.setText("" + viewItem.getLastUnitPrice());
        skuValue.setText(viewItem.getSKUNum());
    }
    
    @FXML
    void handleBackAction(ActionEvent e) throws IOException {
        Parent homePage = FXMLLoader.load(ViewResourceItemController.class.getResource("../imsMenu.fxml"));
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
    }

    @FXML
    void handleHomeAction(ActionEvent e) throws IOException {
        Parent homePage = FXMLLoader.load(ViewResourceItemController.class.getResource("../main.fxml"));
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
