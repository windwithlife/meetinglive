package com.simple.server.auto.generic.dto;

import java.io.Serializable;

import com.simple.server.auto.generic.entity.*;
import com.simple.common.api.BaseResponse;

import java.util.List;
import java.util.ArrayList;


    public class RecommendPositionResponse extends BaseResponse implements Serializable {
        private static final long serialVersionUID = 1L;



    
    private Long id;         
    
    private String name;         
    
    private String description;         
    
    private String pattern;         
    

    public RecommendPositionResponse() {
    } 
   


    
       
    public Long getId(){
        return this.id;
    }   
    public void setId(Long id){
        this.id = id;
    }        
    
       
    public String getName(){
        return this.name;
    }   
    public void setName(String name){
        this.name = name;
    }        
    
       
    public String getDescription(){
        return this.description;
    }   
    public void setDescription(String description){
        this.description = description;
    }        
    
       
    public String getPattern(){
        return this.pattern;
    }   
    public void setPattern(String pattern){
        this.pattern = pattern;
    }        
    

  
}
