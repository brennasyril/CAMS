/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.cams.ims;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import main.java.cams.database.DatabaseController; 
  

/**
 *
 * @author Brenna
 */
public class ResourceItem {

    public SimpleStringProperty itemCode, itemDesc; 
    public SimpleStringProperty itemName, itemCategory;
    public SimpleStringProperty sKUNum; 
    public SimpleIntegerProperty quantityOnHand, reorderQuantity, acctNum, uPCCode;
    public SimpleIntegerProperty IDSeqNum; 
    public SimpleDoubleProperty lastUnitPrice, averageUnitPrice, lastSellingPrice, currentSellingPrice;
    private final DatabaseController db;

    public ResourceItem(String code, String name, String category, int quantity, String desc, int reorder,
            double lastPrice, double averagePrice, double sellingPrice, double currentPrice, int account, int upc, String sku) {
        this.IDSeqNum = new SimpleIntegerProperty(0);
        db = new DatabaseController("ims");
        this.itemCode = new SimpleStringProperty(code);
        this.itemName = new SimpleStringProperty(name);
        this.itemDesc = new SimpleStringProperty(desc); 
        this.itemCategory = new SimpleStringProperty(category);
        this.uPCCode = new SimpleIntegerProperty(upc);
        this.sKUNum = new SimpleStringProperty(sku);
        this.quantityOnHand = new SimpleIntegerProperty(quantity);
        this.reorderQuantity = new SimpleIntegerProperty(reorder);
        this.acctNum = new SimpleIntegerProperty(account);
        this.lastUnitPrice = new SimpleDoubleProperty(lastPrice);
        this.averageUnitPrice = new SimpleDoubleProperty(averagePrice);
        this.lastSellingPrice = new SimpleDoubleProperty(sellingPrice);
        this.currentSellingPrice = new SimpleDoubleProperty(currentPrice);
    }
    
    public ResourceItem(String code, String name) {
        db = new DatabaseController("ims");
        this.itemCode = new SimpleStringProperty(code);
        this.itemName = new SimpleStringProperty(name);
    }

    public void addResourceItem() {

        String query = "INSERT INTO IM_ResourceItem_BR (IMItemCode, IMItemName, IMItemCat, IMQty, IMItemDesc, IMLowQty, "
                + "IMLastPrice, IMAvgPrice, IMPrevPrice, IMCurrPrice, IMAcctNum, IMUPC, IMSKU) " + "VALUES('"
                + this.getItemCode() + "','" + this.getItemName()
                + "','" + this.getItemCategory() + "','"
                + this.getQuantityOnHand() + "','" + this.getItemDesc() + "','"+ this.getReorderQuantity()
                + "','" + this.getLastUnitPrice() + "','"
                + this.getAverageUnitPrice() + "','" + this.getLastSellingPrice()
                + "','" + this.getCurrentSellingPrice() + "','"
                + this.getAcctNum() + "','" + this.getUPCCode()
                + "','" + this.getSKUNum() + "')";

        IDSeqNum.set(db.executeInsert(query));
    }

    public void updateResourceItem() {
        String query = "UPDATE IM_ResourceItem_BR " + "SET IMItemCode = '"
                + this.getItemCode() + "', IMItemName = '" + this.getItemName()
                + "', IMItemCat = '" + this.getItemCategory() + "', IMQty = '"
                + this.getQuantityOnHand() +"', IMItemDesc = '" + this.getItemDesc() + "', IMLowQty = '" + this.getReorderQuantity()
                + "', IMLastPrice = '" + this.getLastUnitPrice() + "', IMAvgPrice = '"
                + this.getAverageUnitPrice() + "', IMPrevPrice = '" + this.getLastSellingPrice()
                + "', IMCurrPrice = '" + this.getCurrentSellingPrice() + "', IMAcctNum = '"
                + this.getAcctNum() + "', IMUPC = '" + this.getUPCCode()
                + "', IMSKU = '" + this.getSKUNum() + "' WHERE (IMItemCode = '"
                + this.getItemCode() + "') and (IMSeqNum = " + this.getID() + ")";

        db.executeStatement(query);
    }
    
    public void deleteResourceItem() {
        String query = "DELETE FROM `ims`.`im_resourceitem_br` WHERE `im_resourceitem_br`.`IMSeqNum` = " + 
                this.getID() + " AND `im_resourceitem_br`.`IMItemCode` = '" + this.getItemCode() + "'"; 

        db.executeStatement(query);
    }

    public int getID() {
        return IDSeqNum.get();
    }
    
    public void setID(int value) {
        IDSeqNum.set(value);
    }
    
    public SimpleIntegerProperty IDSeqNumProperty() {
        return IDSeqNum;
    }

    public String getItemCode() {
        return itemCode.get();
    }

    public void setItemCode(String code) {
        itemCode.set(code);
    }
    
    public SimpleStringProperty itemCodeProperty() {
        return itemCode;
    }
    
    public SimpleStringProperty itemNameProperty() {
        return itemName;
    }

    public String getItemName() {
        return itemName.get();
    }

    public void setItemName(String name) {
        itemName.set(name);
    }
    
    public SimpleStringProperty itemDescProperty() {
        return itemDesc;
    }

    public String getItemDesc() {
        return itemDesc.get();
    }

    public void setItemDesc(String desc) {
        itemDesc.set(desc);
    }

    public String getItemCategory() {
        return itemCategory.get();
    }
    
    public SimpleStringProperty itemCategoryProperty() {
        return itemCategory;
    }

    public void setItemCategory(String category) {
        itemCategory.set(category);
    }

    public int getUPCCode() {
        return uPCCode.get();
    }
    
    public SimpleIntegerProperty uPCCodeProperty() {
        return uPCCode;
    }

    public void setUPCCode(int upc) {
        uPCCode.set(upc);
    }

    public String getSKUNum() {
        return sKUNum.get();
    }
    
    public SimpleStringProperty sKUNumProperty() {
        return sKUNum;
    }

    public void setSKUNum(String sku) {
        sKUNum.set(sku);
    }

    public int getQuantityOnHand() {
        return quantityOnHand.get();
    }
    
    public SimpleIntegerProperty quantityOnHandProperty() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(int quantity) {
        quantityOnHand.set(quantity);
    }

    public int getReorderQuantity() {
        return reorderQuantity.get();
    }

    public SimpleIntegerProperty reorderQuantityProperty() {
        return reorderQuantity;
    }
    
    public void setReorderQuantity(int reorder) {
        reorderQuantity.set(reorder);
    }

    public int getAcctNum() {
        return acctNum.get();
    }

    public SimpleIntegerProperty acctNumProperty() {
        return acctNum;
    }
    
    public void setAcctNum(int account) {
        acctNum.set(account);
    }

    public double getLastUnitPrice() {
        return lastUnitPrice.get();
    }
    
    public SimpleDoubleProperty lastUnitPriceProperty() {
        return lastUnitPrice;
    }

    public void setLastUnitPrice(double price) {
        lastUnitPrice.set(price);
    }

    public double getAverageUnitPrice() {
        return averageUnitPrice.get();
    }
    
    public SimpleDoubleProperty averageUnitPriceProperty() {
        return averageUnitPrice;
    }

    public void setAverageUnitPrice(double price) {
        averageUnitPrice.set(price);
    }

    public double getLastSellingPrice() {
        return lastSellingPrice.get();
    }

    public SimpleDoubleProperty lastSellingPriceProperty() {
        return lastSellingPrice;
    }
    
    public void setLastSellingPrice(double price) {
        lastSellingPrice.set(price);
    }

    public double getCurrentSellingPrice() {
        return currentSellingPrice.get();
    }

    public SimpleDoubleProperty currentSellingPriceProperty() {
        return currentSellingPrice;
    }
    
    public void setCurrentSellingPrice(double price) {
        currentSellingPrice.set(price);
    }
    
    public String toString() {
        String print = itemCode + " " + itemName + " ";
        
        return print; 
    }
}
