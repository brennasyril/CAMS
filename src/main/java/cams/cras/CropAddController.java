/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.cams.cras;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import main.java.cams.database.DatabaseController;
import main.java.cams.ims.ImsMenuController;

public class CropAddController implements Initializable {

    @FXML
    private TextField codeField;

    @FXML
    private Label code;

    @FXML
    private AnchorPane anchor2;

    @FXML
    private TextField nameField;

    @FXML
    private Label main;

    @FXML
    private Button addButton;

    @FXML
    private Label family;

    @FXML
    private ImageView title;

    @FXML
    private ComboBox<String> plantFamDrop;

    @FXML
    private VBox vbox2;

    @FXML
    private ComboBox<String> periodDrop;

    @FXML
    private Button periodButton;

    @FXML
    private VBox vbox1;

    @FXML
    private ComboBox<String> conditionDrop;

    @FXML
    private ComboBox<String> conditionDrop1;

    @FXML
    private ComboBox<String> conditionDrop2;

    @FXML
    private TextField conditionReq;

    @FXML
    private TextField conditionReq1;

    @FXML
    private TextField conditionReq2;

    @FXML
    private Button newCondButton;

    @FXML
    private SplitPane split;

    @FXML
    private HBox hbox;

    @FXML
    private VBox vbox3;

    @FXML
    private AnchorPane anchor;

    @FXML
    private Label name;

    @FXML
    private ImageView logo;

    @FXML
    private Label category;

    @FXML
    private Button existCondButton;

    @FXML
    private Label condition;

    private DatabaseController db = new DatabaseController("cras");

    @FXML
    private VBox extraVBox;

    private int counter = 4;

    


    @FXML
    void handleAddAction(ActionEvent event) {
        String code, name, plantFamChosen, periodChosen;

        code = codeField.getText().trim();
        name = nameField.getText().trim();
        plantFamChosen = plantFamDrop.getSelectionModel().getSelectedItem();
        String famQuery = "Select CRAPlantFamCode from `cra_plantfamily_br` WHERE CRAFamName = \"" + plantFamChosen + "\";";
        String plantFam = db.getSingleQueryResult(famQuery);
        periodChosen = periodDrop.getSelectionModel().getSelectedItem();
        String delims = "[-]";
        String[] dates = periodChosen.split(delims);
        String periodQuery = "Select CRACropPerCode from `cra_cropPeriod_br` WHERE CRAStartDate = \"" + dates[0] + "\" and CRAEndDate = \"" + dates[1] + "\";";
        String period = db.getSingleQueryResult(periodQuery);
        Crop newCrop = new Crop(code, name, period, plantFam);
        newCrop.addCrop();

        String cond1Chosen, cond2Chosen, cond3Chosen, cond1Req, cond2Req, cond3Req;

        cond1Chosen = conditionDrop.getSelectionModel().getSelectedItem();
        String condQuery = "Select CRACondCode from `cra_condition_br` where CRACondName = \"" + cond1Chosen + "\"";
        String cond1 = db.getSingleQueryResult(condQuery);
        cond1Req = conditionReq.getText().trim();
        CropCondMap newMap = new CropCondMap(code, cond1, cond1Req);
        newMap.addCropCondMap();

        cond2Chosen = conditionDrop1.getSelectionModel().getSelectedItem();
        condQuery = "Select CRACondCode from `cra_condition_br` where CRACondName = \"" + cond2Chosen + "\"";
        String cond2 = db.getSingleQueryResult(condQuery);
        cond2Req = conditionReq1.getText().trim();
        newMap.setCondCode(cond2);
        newMap.setCropCondReq(cond2Req);
        newMap.addCropCondMap();

        cond3Chosen = conditionDrop2.getSelectionModel().getSelectedItem();
        condQuery = "Select CRACondCode from `cra_condition_br` where CRACondName = \"" + cond3Chosen + "\"";
        String cond3 = db.getSingleQueryResult(condQuery);
        cond3Req = conditionReq2.getText().trim();
        newMap.setCondCode(cond3);
        newMap.setCropCondReq(cond3Req);
        newMap.addCropCondMap();

        Alert addedAlert = new Alert(Alert.AlertType.INFORMATION);
        addedAlert.setTitle("Confirmation");
        addedAlert.setHeaderText("Congratulations");
        addedAlert.setContentText("Your new Crop has been added");
        addedAlert.showAndWait();
    }

    @FXML
    void handleExistCondAction(ActionEvent e) throws IOException {
        counter--;
        if (counter > 0) {
            extraVBox.setMinWidth(270);
            Label newLabel = new Label("Condition");
            newLabel.setStyle("-fx-font-weight: bold");
            ComboBox<String> newCond = new ComboBox();
            newCond.setPrefWidth(250);
            initializeCondDrop(newCond);
            Label newLabel2 = new Label("Condition Requirements");
            TextField newReq = new TextField();
            newReq.setPrefWidth(250);
            extraVBox.getChildren().addAll(newLabel, newCond, newLabel2, newReq);
        } else {
            existCondButton.setDisable(true);
        }
    }

    @FXML
    void handleNewCondAction(ActionEvent e) throws IOException {
        Parent cropCond = FXMLLoader.load(CrasMenuController.class.getResource("../addCond.fxml"));
        Scene newScene = new Scene(cropCond);
        Stage newStage = new Stage();
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        newStage.setScene(newScene);
        newStage.showAndWait();
        extraVBox.setMinWidth(270);
        Label newLabel = new Label("Condition");
        newLabel.setStyle("-fx-font-weight: bold");
        ComboBox<String> newCond = new ComboBox();
        newCond.setPrefWidth(250);
        initializeCondDrop(newCond);
        Label newLabel2 = new Label("Condition Requirements");
        TextField newReq = new TextField();
        newReq.setPrefWidth(250);
        extraVBox.getChildren().addAll(newLabel, newCond, newLabel2, newReq);
    }

    @FXML
    void handleNewPerAction(ActionEvent e) throws IOException {
        Parent cropPer = FXMLLoader.load(CropAddController.class.getResource("../addCropPer.fxml"));
        Scene newScene = new Scene(cropPer);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.showAndWait();
        initializePeriodDrop(periodDrop);
    }

    @FXML
    void handleBackAction(ActionEvent e) throws IOException {
        Parent homePage = FXMLLoader.load(CrasMenuController.class.getResource("../crasMenu.fxml"));
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

    void initializeCondDrop(ComboBox<String> box) {
        box.getItems().clear();
        String dropDown = "";
        String delims = "[%]";
        dropDown = db.fillComboQuery("select CRACondName from cras.cra_condition_br");
        String[] fill = dropDown.split(delims);
        try {
            for (int i = 0; i < fill.length; i++) {
                box.getItems().add(fill[i]);
            }
        } catch (Exception e) {
        }
    }

    void initializeFamilyDrop(ComboBox<String> box) {
        box.getItems().clear();
        String dropDown = "";
        String delims = "[%]";
        dropDown = db.fillComboQuery("select CRAFamName from cras.cra_plantFamily_br");
        String[] fill = dropDown.split(delims);

        for (int i = 0; i < fill.length; i++) {
            box.getItems().add(fill[i]);
        }
    }

    void initializePeriodDrop(ComboBox<String> box) {
        box.getItems().clear();
        String dropDown = "";
        String delims = "[%]";
        dropDown = db.fillComboQuery("SELECT CONCAT(`CRAStartDate`, '-',`CRAEndDate`) FROM `cra_cropperiod_br` ");
        String[] fill = dropDown.split(delims);

        for (int i = 0; i < fill.length; i++) {
            box.getItems().add(fill[i]);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeCondDrop(conditionDrop);
        initializeCondDrop(conditionDrop1);
        initializeCondDrop(conditionDrop2);
        initializeFamilyDrop(plantFamDrop);
        initializePeriodDrop(periodDrop);
    }

}
