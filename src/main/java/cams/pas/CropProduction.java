/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.cams.pas;

/**
 *
 * @author Brenna
 */
public class CropProduction {
    private String prodRef, cropCode, seasonCode, prodUnit, comment; 
    private int prodQuantity; 
    
    public CropProduction(String ref, String crop, String season, String unit, String comm, int qty) {
        prodRef = ref; 
        cropCode = crop; 
        seasonCode  = season; 
        prodUnit = unit; 
        comment = comm; 
        prodQuantity = qty; 
    }
    
    public String getRef() {
        return prodRef; 
    }
    
    public void setRef(String ref) {
        prodRef = ref; 
    }
    
    public String getCrop() {
        return cropCode; 
    }
    
    public void setCrop(String crop) {
        cropCode = crop; 
    }
    
    public String getSeason() {
        return seasonCode; 
    }
    
    public void setSeason(String season) {
        seasonCode = season;
    }
    
    public String getProdUnit() {
        return prodUnit; 
    }
    
    public void setProdUnit(String unit) {
        prodUnit = unit; 
    }
    
    public String getComment() { 
        return comment; 
    }
    
    public void setComment(String comm) {
        comment = comm; 
    }
    
    public int getProdQty() {
        return prodQuantity; 
    }
    
    public void setProdQty(int qty) {
        prodQuantity = qty; 
    }
}
