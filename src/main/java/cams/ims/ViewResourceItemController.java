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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
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

/**
 * FXML Controller class
 *
 * @author Brenna
 */
public class ViewResourceItemController implements Initializable {

    private TableColumn<ResourceItem, String> code;

    private TableColumn<ResourceItem, Double> avg;

    //@FXML
    private TableColumn<ResourceItem, String> cat;

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

    //@FXML
    private TableColumn<ResourceItem, Integer> qty;

    //@FXML
    private TableColumn<ResourceItem, String> name;

    //@FXML
    private TableColumn<ResourceItem, Integer> reorderQty;

    //@FXML
    private TableColumn<ResourceItem, Integer> acct;
    
    private TableColumn<ResourceItem, String> desc; 

    private ComboBox<String> newAtt;

    private TextField newAttText;

    private Button newSearch;

    private Label newSearchLabel;

    @FXML
    private TextField searchField;

    @FXML
    private AnchorPane anchor2;

    @FXML
    private ScrollPane scroll;

    @FXML
    private Button back;

    @FXML
    private Label main;

    @FXML
    private HBox searchHBox;

    @FXML
    private VBox bodyVBox;

    @FXML
    private Label searchLabel;

    @FXML
    private ImageView title;

    @FXML
    private HBox buttonHBox;

    @FXML
    private Button home;

    @FXML
    private Button search;

    @FXML
    private Button undo;

    @FXML
    private Button view;

    @FXML
    private SplitPane split;

    @FXML
    private AnchorPane anchor;

    @FXML
    private ImageView logo;

    @FXML
    private ComboBox<String> attribute;

    @FXML
    private Button addAttButton;

    @FXML
    private HBox addAttBox;

    private final TableView<ResourceItem> table = new TableView<>();
    private final ObservableList<ResourceItem> data = FXCollections.observableArrayList();
    private final DatabaseController db = new DatabaseController("ims");

    @FXML
    void handleViewAction(ActionEvent e) throws IOException {
        ResourceItem viewItem = null;
        viewItem = table.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlloader = new FXMLLoader(ViewResourceItemController.class.getResource("../resourceItemInquiry.fxml"));
        Parent homePage = (Parent) fxmlloader.load();
        ResourceItemInquiryController newController = fxmlloader.<ResourceItemInquiryController>getController();
        newController.setItem(viewItem);
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
        oldStage.show();
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

    @FXML
    void handleUndoAction(ActionEvent e) throws IOException {
        Parent homePage = FXMLLoader.load(ViewResourceItemController.class.getResource("../viewResourceItem.fxml"));
        Scene homepage = new Scene(homePage);
        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        oldStage.setScene(homepage);
    }

    @FXML
    void handleAddAttAction(ActionEvent e) throws IOException {
        addAttBox.getChildren().clear();
        searchHBox.getChildren().remove(search);
        newAtt = new ComboBox();
        newAttText = new TextField();
        newSearch = new Button("Search");
        newSearchLabel = new Label("Search for Item:");
        newCombo(newAtt);
        addAttBox.getChildren().addAll(newSearchLabel, newAtt, newAttText, newSearch);

        newSearch.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                String searchAtt = attribute.getSelectionModel().getSelectedItem();
                String value = searchField.getText().trim();
                String searchAtt2 = newAtt.getSelectionModel().getSelectedItem(); 
                String value2 = newAttText.getText().trim(); 
                
                searchAtt = getQueryAtt(searchAtt);
                searchAtt2 = getQueryAtt(searchAtt2); 
                String query = "select * from `im_resourceItem_br` where `" + searchAtt + "`='" + value + "' and `" + searchAtt2+  "`='" + value2 + "'";
                ArrayList<String[]> items = new ArrayList<String[]>();

                items = db.getQueryResult(query);

                ObservableList<ResourceItem> newTableData = FXCollections.observableArrayList();
                String searchText = searchField.getText().trim();
                ResourceItem currentItem = null;

                if ("".equals(searchText)) {
                    try {
                        Parent homePage = FXMLLoader.load(AddResourceItemController.class.getResource("../viewResourceItem.fxml"));
                        Scene homepage = new Scene(homePage);
                        Stage oldStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        oldStage.setScene(homepage);
                    } catch (IOException ex) {
                    }
                } else {
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
                        newTableData.add(newItem);
                        System.out.println(newItem.toString());
                    }
                    table.setItems(newTableData);
                    table.getColumns().removeAll(code, seq, name, cat, qty, desc, reorderQty, lastU, avg, lastS, curr, acct, upc, sku);
                    table.getColumns().addAll(code, seq, name, cat, qty, desc, reorderQty, lastU, avg, lastS, curr, acct, upc, sku);
                    table.refresh();
                }
            }
        });
    }

    private String getQueryAtt(String name) {
        String value = "";
        switch (name) {
            case "Sequence Number": {
                value = "IMSeqNum";
                break;
            }
            case "Item Code": {
                value = "IMItemCode";
                break;
            }
            case "Item Name": {
                value = "IMItemName";
                break;
            }
            case "Item Category": {
                value = "IMItemCat";
                break;
            }
            case "Quantity": {
                value = "IMQty";
                break;
            }
            case "Item Description": {
                value = "IMItemDesc"; 
                break; 
            }
            case "Reorder Quantity": {
                value = "IMLowQty";
                break;
            }
            case "Last Unit Price": {
                value = "IMLastPrice";
                break;
            }
            case "Average Price": {
                value = "IMAvgPrice";
                break;
            }
            case "Last Selling Price": {
                value = "IMPrevPrice";
                break;
            }
            case "Current Price": {
                value = "IMCurrPrice";
                break;
            }
            case "Account Number": {
                value = "IMAcctNum";
                break;
            }
            case "UPC Code": {
                value = "IMUPC";
                break;
            }
            case "SKU Number": {
                value = "IMSKU";
                break;
            }
            default: {
                value = "Invalid Field";
                break;
            }
        }
        return value;
    }

    @FXML
    void handleSearchAction(ActionEvent event) throws IOException {
        String searchAtt = attribute.getSelectionModel().getSelectedItem();
        String value = searchField.getText().trim();
        searchAtt = getQueryAtt(searchAtt);
        String query = "select * from `im_resourceItem_br` where `" + searchAtt + "`='" + value + "'";
        ArrayList<String[]> items = new ArrayList<String[]>();

        items = db.getQueryResult(query);

        ObservableList<ResourceItem> newTableData = FXCollections.observableArrayList();
        String searchText = searchField.getText().trim();
        ResourceItem currentItem = null;

        if ("".equals(searchText)) {
            Parent homePage = FXMLLoader.load(AddResourceItemController.class.getResource("../viewResourceItem.fxml"));
            Scene homepage = new Scene(homePage);
            Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            oldStage.setScene(homepage);
        } else {
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
                newTableData.add(newItem);
                System.out.println(newItem.toString());
            }
            table.setItems(newTableData);
            table.getColumns().removeAll(code, seq, name, cat, qty, desc, reorderQty, lastU, avg, lastS, curr, acct, upc, sku);
            table.getColumns().addAll(code, seq, name, cat, qty, desc, reorderQty, lastU, avg, lastS, curr, acct, upc, sku);
            table.refresh();
        }
    }

    public void newCombo(ComboBox<String> combo) {
        String dropDown = "";
        String delims = "[%]";
        dropDown = db.getColumns("select * from ims.im_resourceItem_br");
        String[] fill = dropDown.split(delims);

        for (int i = 0; i < fill.length; i++) {
            String colCode = fill[i];
            switch (colCode) {
                case "IMSeqNum": {
                    colCode = "Sequence Number";
                    break;
                }
                case "IMItemCode": {
                    colCode = "Item Code";
                    break;
                }
                case "IMItemName": {
                    colCode = "Item Name";
                    break;
                }
                case "IMItemCat": {
                    colCode = "Item Category";
                    break;
                }
                case "IMQty": {
                    colCode = "Quantity";
                    break;
                }
                case "IMItemDesc": {
                    colCode = "Item Description"; 
                    break; 
                }
                case "IMLowQty": {
                    colCode = "Reorder Quantity";
                    break;
                }
                case "IMLastPrice": {
                    colCode = "Last Unit Price";
                    break;
                }
                case "IMAvgPrice": {
                    colCode = "Average Price";
                    break;
                }
                case "IMPrevPrice": {
                    colCode = "Last Selling Price";
                    break;
                }
                case "IMCurrPrice": {
                    colCode = "Current Price";
                    break;
                }
                case "IMAcctNum": {
                    colCode = "Account Number";
                    break;
                }
                case "IMUPC": {
                    colCode = "UPC Code";
                    break;
                }
                case "IMSKU": {
                    colCode = "SKU Number";
                    break;
                }
                default: {
                    colCode = "Invalid Field";
                    break;
                }
            }
            combo.getItems().add(colCode);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        newCombo(attribute);

        DatabaseController db = new DatabaseController("ims");

        table.setEditable(true);

        seq = new TableColumn<ResourceItem, Integer>("Sequence Number:");//creates first column
        seq.setMinWidth(100);
        seq.setCellValueFactory(
                new PropertyValueFactory("IDSeqNum"));

        code = new TableColumn<ResourceItem, String>("Item Code:");//creates first column
        code.setMinWidth(100);
        code.setCellValueFactory(
                new PropertyValueFactory("itemCode"));

        name = new TableColumn<>("Item Name:");//creates first column
        name.setMinWidth(100);
        name.setCellValueFactory(
                new PropertyValueFactory("itemName"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<ResourceItem, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ResourceItem, String> t) {
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
                new EventHandler<TableColumn.CellEditEvent<ResourceItem, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ResourceItem, String> t) {
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
        qty.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<ResourceItem, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ResourceItem, Integer> t) {
                        ((ResourceItem) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setQuantityOnHand(t.getNewValue());
                    }
                }
        );
        
        desc = new TableColumn<>("Item Description:");//creates first column
        desc.setMinWidth(100);
        desc.setCellValueFactory(
                new PropertyValueFactory("itemDesc"));
        desc.setCellFactory(TextFieldTableCell.forTableColumn());
        desc.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<ResourceItem, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ResourceItem, String> t) {
                        ((ResourceItem) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setItemDesc(t.getNewValue());
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
                new EventHandler<TableColumn.CellEditEvent<ResourceItem, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ResourceItem, Integer> t) {
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
                new EventHandler<TableColumn.CellEditEvent<ResourceItem, Double>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ResourceItem, Double> t) {
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
                new EventHandler<TableColumn.CellEditEvent<ResourceItem, Double>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ResourceItem, Double> t) {
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
                new EventHandler<TableColumn.CellEditEvent<ResourceItem, Double>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ResourceItem, Double> t) {
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
                new EventHandler<TableColumn.CellEditEvent<ResourceItem, Double>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ResourceItem, Double> t) {
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
                new EventHandler<TableColumn.CellEditEvent<ResourceItem, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ResourceItem, Integer> t) {
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
                new EventHandler<TableColumn.CellEditEvent<ResourceItem, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ResourceItem, String> t) {
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
                new EventHandler<TableColumn.CellEditEvent<ResourceItem, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ResourceItem, Integer> t) {
                        ((ResourceItem) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setUPCCode(t.getNewValue());
                    }
                }
        );

        //String itemCategory = categoryDrop.getSelectionModel().getSelectedItem();
        //String catQuery = "SELECT IMCatCode FROM `im_itemcat_br` WHERE IMCatName = \"" + itemCategory + "\";"; 
        //String catCode = db.getSingleQueryResult(catQuery); 
        ResultSet results;
        String query = "select * from ims.im_resourceItem_br; ";
        ArrayList<String[]> items = new ArrayList<String[]>();

        items = db.getQueryResult(query);
        //itemsInDB.setEditable(false);
        // ResourceItem item = new ResourceItem("broc", "Broccoli", "crop", 45, 3, 35.01, 12.21, 43.44, 33.4, 23422, 2333421, "sds432");
        //ObservableList<ResourceItem> data = FXCollections.observableArrayList();
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

        //itemsInDB.getItems().setAll(data); 
        table.setItems(data);

        table.getColumns().addAll(code, seq, name, cat, qty, desc, reorderQty, lastU, avg, lastS, curr, acct, upc, sku);

        scroll.setContent(table);
    }

}
