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
public class RecommendDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  
    
    private String name; 
        
    
    private String image; 
        
    
    private String description; 
        
    
    private String path; 
        
    
    private Long positionId; 
        
    

    public RecommendDetail() {
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
    
    public String getImage(){
        return this.image;
    }   
    public void setImage(String image){
        this.image = image;
    } 
    
    public String getDescription(){
        return this.description;
    }   
    public void setDescription(String description){
        this.description = description;
    } 
    
    public String getPath(){
        return this.path;
    }   
    public void setPath(String path){
        this.path = path;
    } 
    
    public Long getPositionId(){
        return this.positionId;
    }   
    public void setPositionId(Long positionId){
        this.positionId = positionId;
    } 
    
      
	@Override
	public String toString() {
		return "CLASS DATA: [" +"id=" + this.id +"name="+ this.name +"]";
	}
}
