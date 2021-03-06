package com.simple.server.auto.generic.dto;

import java.io.Serializable;

import com.simple.server.auto.generic.entity.*;
import com.simple.common.api.BaseResponse;

import java.util.List;
import java.util.ArrayList;


    public class RecommendPositionsResponse extends BaseResponse implements Serializable {
        private static final long serialVersionUID = 1L;



    
    private int itemsCount;         
    
    private List<RecommendPositionResponse> items;         
    

    public RecommendPositionsResponse() {
    } 
   


    
       
    public int getItemsCount(){
        return this.itemsCount;
    }   
    public void setItemsCount(int itemsCount){
        this.itemsCount = itemsCount;
    }        
    
       
    public List<RecommendPositionResponse> getItems(){
        return this.items;
    }   
    public void setItems(List<RecommendPositionResponse> items){
        this.items = items;
    }        
    

  
}
