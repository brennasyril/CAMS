/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.cams.cras;

import javafx.beans.property.SimpleStringProperty;
import main.java.cams.database.DatabaseController;

/**
 *
 * @author Brenna
 */
public class CropCondMap {
    
    private SimpleStringProperty cropCode, condCode, cropCondReq;
    DatabaseController db = new DatabaseController("cras");

    public CropCondMap(String crop, String cond, String req) {
        this.cropCode = new SimpleStringProperty(crop);
        this.condCode = new SimpleStringProperty(cond);
        this.cropCondReq = new SimpleStringProperty(req);
    }

    public void addCropCondMap() {

        String query = "INSERT INTO CRA_CropCondMap_BR (CRACropCode, CRACondCode, CRACondReq) " + "VALUES('"
                + this.getCropCode() + "','" + this.getCondCode()
                + "','" + this.getCropCondReq() + "')";
        db.executeInsert(query);
    }

    public void updateCropCondMap() {
        String query = "UPDATE CRA_CropCodMap_BR " + "SET CRACropCode = '"
                + this.getCropCode() + "', CRACondCode = '" + this.getCondCode()
                + "', CRACondReq = '" + this.getCropCondReq() + "' WHERE (CRACropCode = '"
                + this.getCropCode() + "' and CRACondCode = '" + this.getCondCode() + "')";

        db.executeStatement(query);
    }

    public void deleteCropCondMap() {
        String query = "DELETE FROM `cras`.`cra_cropCondMap_br` WHERE `cra_CropCondMap_br`.`CRACropCode` = '" + this.getCropCode() + "' and `cra_CropCondMap_br`.`CRACropCode` = '" + this.getCondCode() + "'";

        db.executeStatement(query);
    }

    public String getCropCode() {
        return cropCode.get();
    }

    public SimpleStringProperty cropCodeProperty() {
        return cropCode;
    }

    public void setCropCode(String code) {
        cropCode.set(code);
    }
    
    public String getCondCode() {
        return condCode.get();
    }

    public SimpleStringProperty condCodeProperty() {
        return condCode;
    }

    public void setCondCode(String code) {
        condCode.set(code);
    }
    
    public String getCropCondReq() {
        return cropCondReq.get();
    }

    public SimpleStringProperty cropCondReqProperty() {
        return cropCondReq;
    }

    public void setCropCondReq(String req) {
        cropCondReq.set(req);
    }
}
