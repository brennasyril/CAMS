/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.cams.ims;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.cams.database.DatabaseController;

public class AddResourceItemController implements Initializable {

    @FXML
    private TextField lastUField;

    @FXML
    private Label code;

    @FXML
    private AnchorPane anchor2;

    @FXML
    private TextField avgField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField skuField;
    
    @FXML
    private TextField descField; 

    @FXML
    private Label main;

    @FXML
    private Label reorder;

    @FXML
    private Label qoh;

    @FXML
    private ImageView title;

    @FXML
    private VBox vbox2;

    @FXML
    private VBox vbox1;

    @FXML
    private Label current;

    @FXML
    private SplitPane split;

    @FXML
    private HBox hbox;

    @FXML
    private Label avg;

    @FXML
    private ImageView logo;

    @FXML
    private Label sku;

    @FXML
    private TextField codeField;

    @FXML
    private Label lastU;

    @FXML
    private Label lastS;

    @FXML
    private TextField currentField;

    @FXML
    private Label upc;

    @FXML
    private Button addButton;

    @FXML
    private TextField qohField;

    @FXML
    private TextField lastSField;

    @FXML
    private TextField upcField;

    @FXML
    private ComboBox<String> categoryDrop;

    @FXML
    private TextField acctField;

    @FXML
    private AnchorPane anchor;

    @FXML
    private Label name;

    @FXML
    private TextField reorderField;

    @FXML
    private Label category;

    @FXML
    private Label acct;

    @FXML
    Button back;

    @FXML
    Button home;

    
    @FXML
    private void handleButtonAction(ActionEvent e) throws IOException {
        DatabaseController db = new DatabaseController("ims"); 
        
        int qtyOH = 0, reorderQty = 0, acctNum = 0, itemUpc = 0;
        double lastSPrice = 0, avgPrice = 0, currentSPrice = 0, lastUPrice = 0;
        String itemCode = codeField.getText().trim();
        String itemName = nameField.getText().trim();
        String itemCategory = categoryDrop.getSelectionModel().getSelectedItem();
        String query = "SELECT IMCatCode FROM `im_itemcat_br` WHERE IMCatName = \"" + itemCategory + "\";"; 
        String catCode = db.getSingleQueryResult(query); 
        String skuNum = skuField.getText().trim();
        String lastUPriceS = lastUField.getText().trim();
        String lastSPriceS = lastSField.getText().trim(); 
        String avgPriceS = avgField.getText().trim(); 
        String currentSPriceS = currentField.getText().trim(); 
        String itemDesc = descField.getText().trim(); 
        
        try {
            qtyOH = Integer.parseInt(qohField.getText().trim());
            reorderQty = Integer.parseInt(reorderField.getText().trim());
            acctNum = Integer.parseInt(acctField.getText().trim());
            itemUpc = Integer.parseInt(upcField.getText().trim());
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Something went wrong");
            alert.setContentText("Entry must be numeric");
        }
        lastUPrice = Double.parseDouble(lastUPriceS);
        lastSPrice = Double.parseDouble(lastSPriceS);
        avgPrice = Double.parseDouble(avgPriceS);
        currentSPrice = Double.parseDouble(currentSPriceS);
        ResourceItem item = new ResourceItem(itemCode, itemName, catCode, qtyOH, itemDesc, reorderQty, lastUPrice, avgPrice, lastSPrice, currentSPrice, acctNum, itemUpc, skuNum);
        item.addResourceItem();
        Alert addedAlert = new Alert(AlertType.INFORMATION);
        addedAlert.setTitle("Confirmation");
        addedAlert.setHeaderText("Congratulations");
        addedAlert.setContentText("Your item has been added to inventory");
        addedAlert.showAndWait();
    }

    @FXML
    private void handleHomeAction(ActionEvent e) throws IOException {
        Parent homePage = FXMLLoader.load(AddResourceItemController.class.getResource("../main.fxml"));
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
    }
    
    @FXML
    private void handleBackAction(ActionEvent e) throws IOException {
        Parent homePage = FXMLLoader.load(ImsMenuController.class.getResource("../imsMenu.fxml"));
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DatabaseController db = new DatabaseController("ims"); 
        String dropDown = ""; 
        String delims = "[%]";
        dropDown = db.fillComboQuery("select IMCatName from ims.im_itemCat_br");
        String[] fill = dropDown.split(delims);
        
        for (int i = 0; i < fill.length; i++) {
            categoryDrop.getItems().add(fill[i]); 
        }
    }

}
