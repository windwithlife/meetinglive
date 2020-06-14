package com.simple.server.auto.generic.dto;

import java.io.Serializable;

import com.simple.server.auto.generic.entity.*;
import com.simple.common.api.BaseResponse;

import java.util.List;
import java.util.ArrayList;


    public class MenusResponse extends BaseResponse implements Serializable {
        private static final long serialVersionUID = 1L;



    
    private int itemsCount;         
    
    private List<MenuResponse> items;         
    

    public MenusResponse() {
    } 
   


    
       
    public int getItemsCount(){
        return this.itemsCount;
    }   
    public void setItemsCount(int itemsCount){
        this.itemsCount = itemsCount;
    }        
    
       
    public List<MenuResponse> getItems(){
        return this.items;
    }   
    public void setItems(List<MenuResponse> items){
        this.items = items;
    }        
    

  
}
