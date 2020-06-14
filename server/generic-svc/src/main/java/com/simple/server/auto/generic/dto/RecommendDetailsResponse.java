package com.simple.server.auto.generic.dto;

import java.io.Serializable;

import com.simple.server.auto.generic.entity.*;
import com.simple.common.api.BaseResponse;

import java.util.List;
import java.util.ArrayList;


    public class RecommendDetailsResponse extends BaseResponse implements Serializable {
        private static final long serialVersionUID = 1L;



    
    private int itemsCount;         
    
    private List<RecommendDetailResponse> items;         
    

    public RecommendDetailsResponse() {
    } 
   


    
       
    public int getItemsCount(){
        return this.itemsCount;
    }   
    public void setItemsCount(int itemsCount){
        this.itemsCount = itemsCount;
    }        
    
       
    public List<RecommendDetailResponse> getItems(){
        return this.items;
    }   
    public void setItems(List<RecommendDetailResponse> items){
        this.items = items;
    }        
    

  
}
