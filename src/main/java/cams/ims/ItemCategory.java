/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.cams.ims;

/**
 *
 * @author Brenna
 */
public class ItemCategory {
    private String categoryCode, categoryDesc; 
    
    public ItemCategory(String code, String desc) {
        categoryCode = code;
        categoryDesc = desc; 
    }
    
    public String getCode() {
        return categoryCode; 
    }
    
    public void setCode(String code) {
        categoryCode = code; 
    }
    
    public String getDesc() {
        return categoryDesc; 
    }
    
    public void setDesc(String desc) {
        categoryDesc = desc; 
    }
}
