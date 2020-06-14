package com.simple.server.auto.generic.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.simple.server.auto.generic.dao.*;
import com.simple.server.auto.generic.entity.*;
import com.simple.server.auto.generic.dto.*;


@Service
public class RecommendPositionService {
	@Autowired
	RecommendPositionRepository dao;
	public RecommendPositionsResponse findAll(){
		return  transferEntity2ResponseListDto(dao.findAll());
		//return items;
	}
	public RecommendPositionsResponse findByName(String name){
		return transferEntity2ResponseListDto(dao.findByName(name));
	}
	public  RecommendPositionsResponse findByNameLike(String name){
    		return transferEntity2ResponseListDto(dao.findByNameLike(name));
    }

	public  RecommendPositionResponse findOneByName(String name){
    		return transferEntity2ResponseDto(dao.findOneByName(name));
    	}

	public RecommendPositionResponse findById(Long id){
		return transferEntity2ResponseDto(dao.findOneById(id));
	}
	public RecommendPositionResponse save(RecommendPositionRequest item){
		RecommendPosition entityObj = transferRequestDto2Entity(item);
		return transferEntity2ResponseDto(this.dao.save(entityObj));
	}

	public RecommendPositionResponse update(RecommendPositionRequest item){

		RecommendPositionResponse result= null;
		
        try{
            RecommendPosition oldEntity = dao.findOneById(item.getId());
          
                oldEntity.setId(item.getId());
          
                oldEntity.setName(item.getName());
          
                oldEntity.setDescription(item.getDescription());
          
                oldEntity.setPattern(item.getPattern());
          
		  RecommendPosition entityObj = dao.save(oldEntity);
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
	
	
	
    public RecommendPosition transferRequestDto2Entity(RecommendPositionRequest inputDto){
		RecommendPosition newEntity = new RecommendPosition();
		
				newEntity.setId(inputDto.getId());
		
				newEntity.setName(inputDto.getName());
		
				newEntity.setDescription(inputDto.getDescription());
		
				newEntity.setPattern(inputDto.getPattern());
		
		return newEntity;
	}

	public RecommendPositionResponse transferEntity2ResponseDto(RecommendPosition entityObj){
		RecommendPositionResponse response = new RecommendPositionResponse();
		
				response.setId(entityObj.getId());
		
				response.setName(entityObj.getName());
		
				response.setDescription(entityObj.getDescription());
		
				response.setPattern(entityObj.getPattern());
		
		return response;
	}

	public RecommendPositionsResponse transferEntity2ResponseListDto(List<RecommendPosition> entityObjs){

		RecommendPositionsResponse responseList = new RecommendPositionsResponse();
        List<RecommendPositionResponse> items = new ArrayList<RecommendPositionResponse>();
	    for(int i=0; i< entityObjs.size(); i++){
			RecommendPositionResponse response = transferEntity2ResponseDto(entityObjs.get(i));
			items.add(response);
			
		}
		responseList.setItems(items);
		responseList.setItemsCount(entityObjs.size());
		return responseList;
		
	}

}
