package com.simple.server.auto.generic.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.simple.server.auto.generic.dao.*;
import com.simple.server.auto.generic.entity.*;
import com.simple.server.auto.generic.dto.*;


@Service
public class MenuService {
	@Autowired
	MenuRepository dao;
	public MenusResponse findAll(){
		return  transferEntity2ResponseListDto(dao.findAll());
		//return items;
	}
	public MenusResponse findByName(String name){
		return transferEntity2ResponseListDto(dao.findByName(name));
	}
	public  MenusResponse findByNameLike(String name){
    		return transferEntity2ResponseListDto(dao.findByNameLike(name));
    }

	public  MenuResponse findOneByName(String name){
    		return transferEntity2ResponseDto(dao.findOneByName(name));
    	}

	public MenuResponse findById(Long id){
		return transferEntity2ResponseDto(dao.findOneById(id));
	}
	public MenuResponse save(MenuRequest item){
		Menu entityObj = transferRequestDto2Entity(item);
		return transferEntity2ResponseDto(this.dao.save(entityObj));
	}

	public MenuResponse update(MenuRequest item){

		MenuResponse result= null;
		
        try{
            Menu oldEntity = dao.findOneById(item.getId());
          
                oldEntity.setId(item.getId());
          
                oldEntity.setName(item.getName());
          
                oldEntity.setUrl(item.getUrl());
          
                oldEntity.setParentId(item.getParentId());
          
                oldEntity.setType(item.getType());
          
                oldEntity.setChannel(item.getChannel());
          
		  Menu entityObj = dao.save(oldEntity);
		  return transferEntity2ResponseDto(entityObj);
        }catch (Exception e){
                System.out.println("***************failed to update item******  ***********");
                e.printStackTrace();
                return null;
        }
		
	}
	public void remove(Long id){
		//this.dao.delete(id);
		this.dao.deleteById(id);
	}
	
	
	
    public Menu transferRequestDto2Entity(MenuRequest inputDto){
		Menu newEntity = new Menu();
		
				newEntity.setId(inputDto.getId());
		
				newEntity.setName(inputDto.getName());
		
				newEntity.setUrl(inputDto.getUrl());
		
				newEntity.setParentId(inputDto.getParentId());
		
				newEntity.setType(inputDto.getType());
		
				newEntity.setChannel(inputDto.getChannel());
		
		return newEntity;
	}

	public MenuResponse transferEntity2ResponseDto(Menu entityObj){
		MenuResponse response = new MenuResponse();
		
				response.setId(entityObj.getId());
		
				response.setName(entityObj.getName());
		
				response.setUrl(entityObj.getUrl());
		
				response.setParentId(entityObj.getParentId());
		
				response.setType(entityObj.getType());
		
				response.setChannel(entityObj.getChannel());
		
		return response;
	}

	public MenusResponse transferEntity2ResponseListDto(List<Menu> entityObjs){

		MenusResponse responseList = new MenusResponse();
        List<MenuResponse> items = new ArrayList<MenuResponse>();
	    for(int i=0; i< entityObjs.size(); i++){
			MenuResponse response = transferEntity2ResponseDto(entityObjs.get(i));
			items.add(response);
			
		}
		responseList.setItems(items);
		responseList.setItemsCount(entityObjs.size());
		return responseList;
		
	}

}
