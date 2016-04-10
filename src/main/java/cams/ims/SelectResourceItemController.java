/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.cams.ims;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import main.java.cams.database.DatabaseController;

public class SelectResourceItemController implements Initializable {

    //@FXML
    private TableColumn<ResourceItem, String> code;

    @FXML
    private Button undo;

    @FXML
    private Button modify;

    @FXML
    private Button delete;

    @FXML
    private TextField searchField;

    @FXML
    private AnchorPane anchor2;

    @FXML
    private Button back;

    @FXML
    private Label main;

    @FXML
    private VBox bodyVBox;

    @FXML
    private ImageView title;

    @FXML
    private HBox buttonHBox;

    @FXML
    private Button search;

    @FXML
    private SplitPane split;

    //@FXML
    private TableColumn<ResourceItem, Double> avg;

    //@FXML
    private TableColumn<ResourceItem, String> cat;
    
    private TableColumn<ResourceItem, String> desc; 

    @FXML
    private ImageView logo;

    //@FXML
    private TableColumn<ResourceItem, Double> curr;

    //@FXML
    private TableColumn<ResourceItem, String> sku;

    //@FXML
    private TableColumn<ResourceItem, Integer> seq;

    //@FXML
    private TableColumn<ResourceItem, Double> lastU;

    //@FXML
    private TableColumn<ResourceItem, Double> lastS;

    //@FXML
    private TableColumn<ResourceItem, Integer> upc;

    @FXML
    private HBox searchHBox;

    @FXML
    private Label searchLabel;

    @FXML
    private Button home;

    @FXML
    private AnchorPane anchor;

    //@FXML
    private TableColumn<ResourceItem, Integer> qty;

    //@FXML
    private TableColumn<ResourceItem, String> name;

    //@FXML
    private TableColumn<ResourceItem, Integer> reorderQty;

    //@FXML
    private TableColumn<ResourceItem, Integer> acct;

    //@FXML
    TableView<ResourceItem> itemsInDB;

    @FXML
    ScrollPane scroll;

    private TableView<ResourceItem> table = new TableView<>();
    ObservableList<ResourceItem> data = FXCollections.observableArrayList();

    private ComboBox<String> categoryDrop;

    @FXML
    private void handleHomeAction(ActionEvent e) throws IOException {
        Parent homePage = FXMLLoader.load(SelectResourceItemController.class.getResource("../main.fxml"));
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

    @FXML
    private void handleUndoAction(ActionEvent e) throws IOException {
        Parent homePage = FXMLLoader.load(AddResourceItemController.class.getResource("../selectResourceItem.fxml"));
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
    }

    @FXML
    private void handleModifyAction(ActionEvent e) throws IOException {
        ResourceItem modifyItem = null;
        modifyItem = table.getSelectionModel().getSelectedItem();
        modifyItem.updateResourceItem();
        Parent homePage = FXMLLoader.load(AddResourceItemController.class.getResource("../selectResourceItem.fxml"));
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
    }

    @FXML
    private void handleDeleteAction(ActionEvent e) throws IOException {
        ResourceItem deleteItem = null;
        deleteItem = table.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        //addedAlert.setHeaderText("Are ");
        alert.setContentText("Are you sure you want to delete this Item? ");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            deleteItem.deleteResourceItem();
            Parent homePage = FXMLLoader.load(AddResourceItemController.class.getResource("../selectResourceItem.fxml"));
            Scene homepage = new Scene(homePage);
            Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            oldStage.setScene(homepage);
        }

    }

    @FXML
    private void handleSearchAction(ActionEvent e) throws IOException {
        ObservableList<ResourceItem> searchList = FXCollections.observableArrayList();
        String itemName = searchField.getText().trim();
        ResourceItem currentItem = null;

        if ("".equals(itemName)) {
            Parent homePage = FXMLLoader.load(AddResourceItemController.class.getResource("../selectResourceItem.fxml"));
            Scene homepage = new Scene(homePage);
            Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            oldStage.setScene(homepage);
        } else {
            for (ResourceItem data1 : data) {
                currentItem = data1;
                if (currentItem.getItemName().equals(itemName)) {
                    searchList.add(currentItem);
                }
            }
            table.setItems(searchList);
            table.refresh();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert search != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert undo != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert modify != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        assert delete != null : "fx:id=\"colName\" was not injected: check your FXML file 'Main.fxml'.";
        DatabaseController db = new DatabaseController("ims");

        table.setEditable(true);

        seq = new TableColumn<>("Sequence Number:");//creates first column
        seq.setMinWidth(100);
        seq.setCellValueFactory(
                new PropertyValueFactory("IDSeqNum"));

        code = new TableColumn<>("Item Code:");//creates first column
        code.setMinWidth(100);
        code.setCellValueFactory(
                new PropertyValueFactory("itemCode"));

        name = new TableColumn<>("Item Name:");//creates first column
        name.setMinWidth(100);
        name.setCellValueFactory(
                new PropertyValueFactory("itemName"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(
                new EventHandler<CellEditEvent<ResourceItem, String>>() {
                    @Override
                    public void handle(CellEditEvent<ResourceItem, String> t) {
                        ((ResourceItem) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setItemName(t.getNewValue());
                    }
                }
        );

        String dropDown = "";
        String delims = "[%]";
        dropDown = db.fillComboQuery("select IMCatName from ims.im_itemCat_br");
        String[] fill = dropDown.split(delims);
        ObservableList<String> levelChoice = FXCollections.observableArrayList();

        for (int i = 0; i < fill.length; i++) {
            levelChoice.add(fill[i]);
        }

        cat = new TableColumn<>("Item Category:");//creates first column
        cat.setMinWidth(100);
        cat.setCellValueFactory(
                new PropertyValueFactory("itemCategory"));
        cat.setCellFactory(ComboBoxTableCell.forTableColumn(levelChoice));
        cat.setOnEditCommit(
                new EventHandler<CellEditEvent<ResourceItem, String>>() {
                    @Override
                    public void handle(CellEditEvent<ResourceItem, String> t) {
                        String itemCategory = t.getNewValue();
                        String query = "SELECT IMCatCode FROM `im_itemcat_br` WHERE IMCatName = \"" + itemCategory + "\";";
                        String catCode = db.getSingleQueryResult(query);
                        ((ResourceItem) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setItemCategory(catCode);
                    }
                }
        );

        qty = new TableColumn<>("Quantity:");//creates first column
        qty.setMinWidth(100);
        qty.setCellValueFactory(new PropertyValueFactory("quantityOnHand"));
        qty.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {

            @Override
            public String toString(Integer object) {
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }

        }));
        
        desc = new TableColumn<>("Item Description:");//creates first column
        desc.setMinWidth(100);
        desc.setCellValueFactory(
                new PropertyValueFactory("itemDesc"));
        desc.setCellFactory(TextFieldTableCell.forTableColumn());
        desc.setOnEditCommit(
                new EventHandler<CellEditEvent<ResourceItem, String>>() {
                    @Override
                    public void handle(CellEditEvent<ResourceItem, String> t) {
                        ((ResourceItem) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setItemDesc(t.getNewValue());
                    }
                }
        );
        
        qty.setOnEditCommit(
                new EventHandler<CellEditEvent<ResourceItem, Integer>>() {
                    @Override
                    public void handle(CellEditEvent<ResourceItem, Integer> t) {
                        ((ResourceItem) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setQuantityOnHand(t.getNewValue());
                    }
                }
        );

        reorderQty = new TableColumn<>("Reorder Quantity:");//creates first column
        reorderQty.setMinWidth(100);
        reorderQty.setCellValueFactory(new PropertyValueFactory("reorderQuantity"));
        reorderQty.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {

            @Override
            public String toString(Integer object) {
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }

        }));
        reorderQty.setOnEditCommit(
                new EventHandler<CellEditEvent<ResourceItem, Integer>>() {
                    @Override
                    public void handle(CellEditEvent<ResourceItem, Integer> t) {
                        ((ResourceItem) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setReorderQuantity(t.getNewValue());
                    }
                }
        );

        lastU = new TableColumn<>("Last Unit Price:");//creates first column
        lastU.setMinWidth(100);
        lastU.setCellValueFactory(new PropertyValueFactory("lastUnitPrice"));
        lastU.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {

            @Override
            public Double fromString(String string) {
                return Double.parseDouble(string);
            }

            @Override
            public String toString(Double object) {
                return object.toString();
            }

        }));
        lastU.setOnEditCommit(
                new EventHandler<CellEditEvent<ResourceItem, Double>>() {
                    @Override
                    public void handle(CellEditEvent<ResourceItem, Double> t) {
                        ((ResourceItem) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setLastUnitPrice(t.getNewValue());
                    }
                }
        );

        avg = new TableColumn<>("Average Price:");//creates first column
        avg.setMinWidth(100);
        avg.setCellValueFactory(new PropertyValueFactory("averageUnitPrice"));
        avg.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {

            @Override
            public Double fromString(String string) {
                return Double.parseDouble(string);
            }

            @Override
            public String toString(Double object) {
                return object.toString();
            }

        }));
        avg.setOnEditCommit(
                new EventHandler<CellEditEvent<ResourceItem, Double>>() {
                    @Override
                    public void handle(CellEditEvent<ResourceItem, Double> t) {
                        ((ResourceItem) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setAverageUnitPrice(t.getNewValue());
                    }
                }
        );

        lastS = new TableColumn<>("Last Selling Price:");//creates first column
        lastS.setMinWidth(100);
        lastS.setCellValueFactory(new PropertyValueFactory("lastSellingPrice"));
        lastS.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {

            @Override
            public Double fromString(String string) {
                return Double.parseDouble(string);
            }

            @Override
            public String toString(Double object) {
                return object.toString();
            }

        }));
        lastS.setOnEditCommit(
                new EventHandler<CellEditEvent<ResourceItem, Double>>() {
                    @Override
                    public void handle(CellEditEvent<ResourceItem, Double> t) {
                        ((ResourceItem) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setLastSellingPrice(t.getNewValue());
                    }
                }
        );

        curr = new TableColumn<>("Current Price:");//creates first column
        curr.setMinWidth(100);
        curr.setCellValueFactory(new PropertyValueFactory("currentSellingPrice"));
        curr.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {

            @Override
            public Double fromString(String string) {
                return Double.parseDouble(string);
            }

            @Override
            public String toString(Double object) {
                return object.toString();
            }

        }));
        curr.setOnEditCommit(
                new EventHandler<CellEditEvent<ResourceItem, Double>>() {
                    @Override
                    public void handle(CellEditEvent<ResourceItem, Double> t) {
                        ((ResourceItem) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setCurrentSellingPrice(t.getNewValue());
                    }
                }
        );

        acct = new TableColumn<>("Account Number:");//creates first column
        acct.setMinWidth(100);
        acct.setCellValueFactory(new PropertyValueFactory("acctNum"));
        acct.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {

            @Override
            public String toString(Integer object) {
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }

        }));
        acct.setOnEditCommit(
                new EventHandler<CellEditEvent<ResourceItem, Integer>>() {
                    @Override
                    public void handle(CellEditEvent<ResourceItem, Integer> t) {
                        ((ResourceItem) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setAcctNum(t.getNewValue());
                    }
                }
        );

        sku = new TableColumn<>("SKU Number:");//creates first column
        sku.setMinWidth(100);
        sku.setCellValueFactory(new PropertyValueFactory("sKUNum"));
        sku.setCellFactory(TextFieldTableCell.forTableColumn());
        sku.setOnEditCommit(
                new EventHandler<CellEditEvent<ResourceItem, String>>() {
                    @Override
                    public void handle(CellEditEvent<ResourceItem, String> t) {
                        ((ResourceItem) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setSKUNum(t.getNewValue());
                    }
                }
        );

        upc = new TableColumn<>("UPC Code:");//creates first column
        upc.setMinWidth(100);
        upc.setCellValueFactory(new PropertyValueFactory("uPCCode"));
        upc.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {

            @Override
            public String toString(Integer object) {
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }

        }));
        upc.setOnEditCommit(
                new EventHandler<CellEditEvent<ResourceItem, Integer>>() {
                    @Override
                    public void handle(CellEditEvent<ResourceItem, Integer> t) {
                        ((ResourceItem) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setUPCCode(t.getNewValue());
                    }
                }
        );
        ResultSet results;
        String query = "select * from ims.im_resourceItem_br; ";
        ArrayList<String[]> items = new ArrayList<String[]>();

        items = db.getQueryResult(query);
        data.removeAll(data);
        for (int i = 0; i < items.size(); i++) {
            int itemSeqDB = Integer.parseInt(items.get(i)[0]);
            String itemCodeDB = items.get(i)[1];
            String itemNameDB = items.get(i)[2];
            String itemCatDB = items.get(i)[3];
            int qtyDB = Integer.parseInt(items.get(i)[4]);
            String itemDescDB = items.get(i)[5]; 
            int reorderDB = Integer.parseInt(items.get(i)[6]);
            double lastUDB = Double.parseDouble(items.get(i)[7]);
            double avgDB = Double.parseDouble(items.get(i)[8]);
            double lastSDB = Double.parseDouble(items.get(i)[9]);
            double currDB = Double.parseDouble(items.get(i)[10]);
            int acctDB = Integer.parseInt(items.get(i)[11]);
            String skuDB = items.get(i)[13];
            int upcDB = Integer.parseInt(items.get(i)[12]);
            ResourceItem newItem = new ResourceItem(itemCodeDB, itemNameDB, itemCatDB, qtyDB, itemDescDB, reorderDB, lastUDB, avgDB, lastSDB, currDB, acctDB, upcDB, skuDB);
            newItem.setID(itemSeqDB);
            data.add(newItem);
        }
        table.setItems(data);

        table.getColumns().addAll(code, seq, name, cat, qty, desc, reorderQty, lastU, avg, lastS, curr, acct, upc, sku);

        scroll.setContent(table);
    }

}
