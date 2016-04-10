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
public class PerformanceForecast {
    private String forecastRef, cropCode, seasonCode, perfRef; 
    private double revenueGen, revConfBand, yieldConfBand; 
    private int projYield; 
    
    public PerformanceForecast(String ref, String crop, String season, String perf, double rev, double revBand, double yieldBand, int yield) {
        forecastRef = ref; 
        cropCode = crop; 
        seasonCode = season; 
        perfRef = perf; 
        revenueGen = rev; 
        revConfBand = revBand; 
        yieldConfBand = yieldBand; 
        projYield = yield; 
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
    
    public String getPerfRef() {
        return perfRef; 
    }
    
    public void setPerfRef(String ref) {
        perfRef = ref;  
    }
    
    public double getRevenue() {
        return revenueGen; 
    }
    
    public void setRevenue(double rev) {
        revenueGen = rev; 
    }
    
    public double getRevConfBand() {
        return revConfBand; 
    }
    
    public void setRevConfBand(double band) {
        revConfBand = band; 
    }
    
    public double getYieldConfBand() {
        return yieldConfBand; 
    }
    
    public void setYieldConfBand(double band) {
        yieldConfBand = band; 
    }
    
    public int getYield() {
        return projYield; 
    }
    
    public void setYield(int yield) {
        projYield = yield; 
    }
}
