package com.simple.server.auto.generic.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.simple.server.auto.generic.dao.*;
import com.simple.server.auto.generic.entity.*;
import com.simple.server.auto.generic.dto.*;


@Service
public class InfoService {
	@Autowired
	InfoRepository dao;
	public InfosResponse findAll(){
		return  transferEntity2ResponseListDto(dao.findAll());
		//return items;
	}
	public InfosResponse findByName(String name){
		return transferEntity2ResponseListDto(dao.findByName(name));
	}
	public  InfosResponse findByNameLike(String name){
    		return transferEntity2ResponseListDto(dao.findByNameLike(name));
    }

	public  InfoResponse findOneByName(String name){
    		return transferEntity2ResponseDto(dao.findOneByName(name));
    	}

	public InfoResponse findById(Long id){
		return transferEntity2ResponseDto(dao.findOneById(id));
	}
	public InfoResponse save(InfoRequest item){
		Info entityObj = transferRequestDto2Entity(item);
		return transferEntity2ResponseDto(this.dao.save(entityObj));
	}

	public InfoResponse update(InfoRequest item){

		InfoResponse result= null;
		
        try{
            Info oldEntity = dao.findOneById(item.getId());
          
                oldEntity.setId(item.getId());
          
                oldEntity.setName(item.getName());
          
                oldEntity.setContent(item.getContent());
          
                oldEntity.setTags(item.getTags());
          
                oldEntity.setImage(item.getImage());
          
                oldEntity.setTitle(item.getTitle());
          
		  Info entityObj = dao.save(oldEntity);
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
	
	
	
    public Info transferRequestDto2Entity(InfoRequest inputDto){
		Info newEntity = new Info();
		
				newEntity.setId(inputDto.getId());
		
				newEntity.setName(inputDto.getName());
		
				newEntity.setContent(inputDto.getContent());
		
				newEntity.setTags(inputDto.getTags());
		
				newEntity.setImage(inputDto.getImage());
		
				newEntity.setTitle(inputDto.getTitle());
		
		return newEntity;
	}

	public InfoResponse transferEntity2ResponseDto(Info entityObj){
		InfoResponse response = new InfoResponse();
		
				response.setId(entityObj.getId());
		
				response.setName(entityObj.getName());
		
				response.setContent(entityObj.getContent());
		
				response.setTags(entityObj.getTags());
		
				response.setImage(entityObj.getImage());
		
				response.setTitle(entityObj.getTitle());
		
		return response;
	}

	public InfosResponse transferEntity2ResponseListDto(List<Info> entityObjs){

		InfosResponse responseList = new InfosResponse();
        List<InfoResponse> items = new ArrayList<InfoResponse>();
	    for(int i=0; i< entityObjs.size(); i++){
			InfoResponse response = transferEntity2ResponseDto(entityObjs.get(i));
			items.add(response);
			
		}
		responseList.setItems(items);
		responseList.setItemsCount(entityObjs.size());
		return responseList;
		
	}

}
