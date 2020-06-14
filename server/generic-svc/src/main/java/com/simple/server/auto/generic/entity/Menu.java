package com.simple.server.auto.generic.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.*;


import com.simple.server.auto.generic.entity.*;

import java.util.List;

@Entity
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  
    
    private String name; 
        
    
    private String url; 
        
    
    private Long parentId; 
        
    
    private String type; 
        
    
    private String channel; 
        
    

    public Menu() {
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
    
      
	@Override
	public String toString() {
		return "CLASS DATA: [" +"id=" + this.id +"name="+ this.name +"]";
	}
}
