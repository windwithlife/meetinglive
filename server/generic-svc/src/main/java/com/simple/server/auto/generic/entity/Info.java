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
public class Info implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  
    
    private String name; 
        
    
    @Column(columnDefinition="text")
    private String content;      
        
    
    private String tags; 
        
    
    private String image; 
        
    
    private String title; 
        
    

    public Info() {
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
    
      
	@Override
	public String toString() {
		return "CLASS DATA: [" +"id=" + this.id +"name="+ this.name +"]";
	}
}
