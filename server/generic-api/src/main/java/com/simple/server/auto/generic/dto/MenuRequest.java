package com.simple.server.auto.generic.dto;

import java.io.Serializable;

import com.simple.server.auto.generic.entity.*;
import com.simple.common.api.BaseResponse;

import java.util.List;
import java.util.ArrayList;


    public class MenuRequest implements Serializable {
        private static final long serialVersionUID = 1L;



    
    private Long id;         
    
    private String name;         
    
    private String url;         
    
    private Long parentId;         
    
    private String type;         
    
    private String channel;         
    

    public MenuRequest() {
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
    
       
    public String getUrl(){
        return this.url;
    }   
    public void setUrl(String url){
        this.url = url;
    }        
    
       
    public Long getParentId(){
        return this.parentId;
    }   
    public void setParentId(Long parentId){
        this.parentId = parentId;
    }        
    
       
    public String getType(){
        return this.type;
    }   
    public void setType(String type){
        this.type = type;
    }        
    
       
    public String getChannel(){
        return this.channel;
    }   
    public void setChannel(String channel){
        this.channel = channel;
    }        
    

  
}
