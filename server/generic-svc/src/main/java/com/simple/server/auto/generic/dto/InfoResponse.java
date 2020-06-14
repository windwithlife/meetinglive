package com.simple.server.auto.generic.dto;

import java.io.Serializable;

import com.simple.server.auto.generic.entity.*;
import com.simple.common.api.BaseResponse;

import java.util.List;
import java.util.ArrayList;


    public class InfoResponse extends BaseResponse implements Serializable {
        private static final long serialVersionUID = 1L;



    
    private Long id;         
    
    private String name;         
    
    private String content;         
    
    private String tags;         
    
    private String image;         
    
    private String title;         
    

    public InfoResponse() {
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
    
       
    public String getContent(){
        return this.content;
    }   
    public void setContent(String content){
        this.content = content;
    }        
    
       
    public String getTags(){
        return this.tags;
    }   
    public void setTags(String tags){
        this.tags = tags;
    }        
    
       
    public String getImage(){
        return this.image;
    }   
    public void setImage(String image){
        this.image = image;
    }        
    
       
    public String getTitle(){
        return this.title;
    }   
    public void setTitle(String title){
        this.title = title;
    }        
    

  
}
