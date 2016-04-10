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
public class ResourceForecast {
    private String forecastRef, cropCode, seasonCode, resCode, resLogRef; 
    private int qtyAllocated; 
    private double resCost, qtyConfBand, costConfBand; 
    
    public ResourceForecast(String ref, String crop, String resource, String logRef, String season, int qty, double cost, double costBand, double qtyBand) {
        forecastRef = ref; 
        cropCode = crop; 
        seasonCode = season; 
        resCode = resource; 
        resLogRef = logRef; 
        qtyAllocated = qty; 
        resCost = cost; 
        qtyConfBand = qtyBand; 
        costConfBand = costBand; 
    }
    
    public String getForecastRef() {
        return forecastRef; 
    }
    
    public void setForecastRef(String ref) {
        forecastRef = ref; 
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
    
    public String getResource() {
        return resCode; 
    }
    
    public void setResource(String ref) {
        resCode = ref; 
    }
    
    public String getResLog() {
        return resLogRef; 
    }
    
    public void setResLog(String ref) {
        resLogRef = ref; 
    }
    
    public int getQuantity() {
        return qtyAllocated; 
    }
    
    public void setQuantity(int qty) {
        qtyAllocated = qty; 
    }
    
    public double getCost() {
        return resCost; 
    }
    
    public void setCost(double cost) {
        resCost = cost; 
    }
    
    public double getQtyBnd() {
        return qtyConfBand; 
    }
    
    public void setQtyBand(double band) {
        qtyConfBand = band; 
    }
    
    public double getCostBand() {
        return costConfBand; 
    }
    
    public void setCostBand(double band) {
        costConfBand = band; 
    }
}
